package uy.viruscontrol.bussines;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import uy.viruscontrol.bussines.enumerated.TipoCaso;
import uy.viruscontrol.bussines.enumerated.TipoNotificacion;
import uy.viruscontrol.bussines.interfaces.ApplicationBeanLocal;
import uy.viruscontrol.bussines.interfaces.GerenteBeanLocal;
import uy.viruscontrol.bussines.map.MapaInteractivoBeanLocal;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentFirebaseLocal;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorExamenLocal;
import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.ConfiguracionNotificacionesDAOLocal;
import uy.viruscontrol.model.dao.interfaces.GerenteDAOLocal;
import uy.viruscontrol.model.dao.interfaces.UsuarioDAOLocal;
import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Caso;
import uy.viruscontrol.model.entities.ConfiguracionNotificaciones;
import uy.viruscontrol.model.entities.EstadoExamen;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.ldap.LDAPConexion;
import uy.viruscontrol.utils.ResultadoExamen;
import uy.viruscontrol.utils.firebase.NotificationInfo;
import uy.viruscontrol.utils.firebase.NotificationInfoData;
import uy.viruscontrol.utils.firebase.NotificationPriority;

/**
 *
  Descripción general del objeto
  ----------------------------------------------
  		Singleton que corre al levantar la aplicacion, para levantar diferentes tareas automaticas que realice 
   viruscontrol.
  
  		Funcionalidades:
  			- Hilo que verifica los examenes pendientes en viruscontrol y consulta con el periferico de examenes si 
  			los examenes tienen resultado.
  			- Actualizacion del mapa cada una hora.
  			- Notifica sobre nuevos casos, según configuración precargada por el gerente
 * 
 **/
@Startup
@Singleton
@Local(ApplicationBeanLocal.class)
public class ApplicationBean implements ApplicationBeanLocal {

	private static final long UNA_HORA = 3600000;

	@EJB private CasoDAOLocal casoDAO;
	@EJB private ServiceAgentProveedorExamenLocal sagProvExamen;
	@EJB private MapaInteractivoBeanLocal mapaBean;
	@EJB private GerenteBeanLocal gerenteBean;
	@EJB private ConfiguracionNotificacionesDAOLocal configNotifDAO;
	@EJB private GerenteDAOLocal gerenteDAO;
	@EJB private UsuarioDAOLocal usuDAO;
	@EJB private ServiceAgentFirebaseLocal saFirebase;
	
    public ApplicationBean() {}
    
    @PostConstruct
    public void iniciarAplicacion() {
    	insertMissingToLDAP();
    	
    	initThreadProcesamientoCasos();
    	initThreadNotificacionesCambioCaso();
    }
    
    private void initThreadProcesamientoCasos() {
    	Thread th = new Thread() {
    		public void run() {
    			
    			while (true) {
    				try {
    					updateCasos();
    					actualizarMapaInteractivo();
						Thread.sleep(UNA_HORA);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    			}
    		}
    	};
    	
    	th.setDaemon(false);
    	th.start();
    }
    
    private void initThreadNotificacionesCambioCaso() {
    	Thread th = new Thread() {
    		public void run() {
    			while (true) {
    				try {
    					notificarCambiosEnCasos();
						Thread.sleep(CINCO_MIN);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    			}
    		}
    	};
    	
    	th.setDaemon(false);
    	th.start();
    }
    
    
    private void updateCasos() {
    	List<Caso> casos = casoDAO.findAll();
    	List<Caso> casosSospechosos = new ArrayList<Caso>();
    	for (Caso caso : casos) {
			if (caso.getTipoCaso().equals(TipoCaso.SOSPECHOSO))
				casosSospechosos.add(caso);
		}
    	if(!casosSospechosos.isEmpty()) 
    		for (Caso caso : casosSospechosos) {
    			try {
    				ResultadoExamen resultado = sagProvExamen.obtenerResultadoExamen(caso.getId()); 
					EstadoExamen estado = resultado.getResultado(); 
					System.out.println("El examen esta: " + estado);
					boolean update;
					switch(estado) {
						case NEGATIVO:
							update = true;
							caso.setTipoCaso(TipoCaso.DESCARTADO);
							break;
						case POSITIVO:
							caso.setTipoCaso(TipoCaso.CONFIRMADO);
							update = true;
							break;
						default:
							update = false;
							break;
					}
					if (update) {
						caso.setNotificacionEnviada(false);
						casoDAO.merge(caso);
						notificarResultadoExamenDisponible(caso.getCiudadano().getTokenPushNotifications(), caso.getId(), estado);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
    	else
    		System.out.println("No hay casos a analizar.");
    }
    
    private void actualizarMapaInteractivo() {
    	mapaBean.loadCasosOnMapa();
    }
    
    private void notificarCambiosEnCasos() {
		List<Caso> aNotificar = casoDAO.findAllNotNotificated();
		ConfiguracionNotificaciones configuracion = configNotifDAO.findById(TipoNotificacion.CAMBIOESTADOCASO);
		if (configuracion != null) {
			System.out.println(configuracion);
			List<Gerente> gerentes = null;
			List<String> correosGerentes = null;
			if (configuracion.isNotificarGerentes()) {
				gerentes = gerenteDAO.findAll();
				correosGerentes = new ArrayList<String>();
				for (Gerente g : gerentes)
					correosGerentes.add(g.getCorreo());
			}
			
			for (Caso it : aNotificar) {
				String asunto = "VirusControlUY ::: Información del caso "+it.getId();
				
				if (configuracion.isNotificarCiudadano() && it.getCiudadano() != null && it.getCiudadano().getCorreo() != null)
					gerenteBean.mandarMail(it.getCiudadano().getCorreo(), asunto, notifCasoMensajeHtml(it.getCiudadano().getNombre()+" "+it.getCiudadano().getApellido(), it.getId(), it.getEnfermedad().getNombre(), it.getTipoCaso().toString()));
				
				if (configuracion.isNotificarMedico() && it.getMedico() != null && it.getMedico().getCorreo() != null)
					gerenteBean.mandarMail(it.getMedico().getCorreo(), asunto, notifCasoMensajeHtml(it.getCiudadano().getNombre()+" "+it.getCiudadano().getApellido(), it.getId(), it.getEnfermedad().getNombre(), it.getTipoCaso().toString()));
				
				if (configuracion.isNotificarGerentes())
					gerenteBean.mandarMail(correosGerentes, asunto, notifCasoMensajeHtml(it.getCiudadano().getNombre()+" "+it.getCiudadano().getApellido(), it.getId(), it.getEnfermedad().getNombre(), it.getTipoCaso().toString()));
				
				it.setNotificacionEnviada(true);
				casoDAO.merge(it);
			}
		} else
			System.out.println("["+getClass().getCanonicalName()+"] ERROR: No se encontró una configuración de notificaciones para el tipo "+TipoNotificacion.CAMBIOESTADOCASO+". No se puede realizar envío.");
	}
    
    private String notifCasoMensajeHtml(String nombre, int idCaso, String enfermedad, String estado) {
    	return "<p>Estimado "+nombre+",</p>"+
    			"<p>Notificamos mediante este correo que se ha presentado un cambio en el caso número "+idCaso+".</p>"+
    			"<p>Enfermedad: "+enfermedad+"</p>"+
    			"<p>Nuevo estado: "+estado+"</p>"+
    			"<p>Atentamente,<p>"+
    			"<p>Equipo de VirusControlUY</p>";
    }

    private void insertMissingToLDAP() {
    	/* Creo en el ldap si no existe el usuario admin y gerente para que sea consistente con la base de datos. */
    	
    	Administrador admin = (Administrador) usuDAO.findByUsername("admin");
    	Gerente gerente = (Gerente) usuDAO.findByUsername("gerente");
    	
    	LDAPConexion ldap = LDAPConexion.getInstancia();
    	
    	if (!ldap.autenticarUsuario(admin))
    		ldap.insertarUsuario(admin);
    	if (!ldap.autenticarUsuario(gerente))
    		ldap.insertarUsuario(gerente);
    }
    
    private void notificarResultadoExamenDisponible(String receiverToken, int idCaso, EstadoExamen estado) {
		// envío notificación push al componente móvil
    	NotificationInfo notificacion = new NotificationInfo(receiverToken, NotificationPriority.normal,
											new NotificationInfoData("Resultado de examen disponible", 
																	"El resultado del examen "+idCaso+" es "+estado));
		saFirebase.sendPushNotification(notificacion);
    }

}
