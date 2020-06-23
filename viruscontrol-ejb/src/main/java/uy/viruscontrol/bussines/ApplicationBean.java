package uy.viruscontrol.bussines;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import uy.viruscontrol.bussines.enumerated.TipoCaso;
import uy.viruscontrol.bussines.enumerated.TipoNotificacion;
import uy.viruscontrol.bussines.interfaces.ApplicationBeanLocal;
import uy.viruscontrol.bussines.interfaces.CiudadanoBeanLocal;
import uy.viruscontrol.bussines.interfaces.GerenteBeanLocal;
import uy.viruscontrol.bussines.map.MapaInteractivoBeanLocal;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentFirebaseLocal;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorExamenLocal;
import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.ConfiguracionNotificacionesDAOLocal;
import uy.viruscontrol.model.dao.interfaces.EnfermedadDAOLocal;
import uy.viruscontrol.model.dao.interfaces.GerenteDAOLocal;
import uy.viruscontrol.model.dao.interfaces.NotificacionDAOLocal;
import uy.viruscontrol.model.dao.interfaces.UbicacionDAOLocal;
import uy.viruscontrol.model.dao.interfaces.UsuarioDAOLocal;
import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Caso;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.ConfiguracionNotificaciones;
import uy.viruscontrol.model.entities.EstadoExamen;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.entities.Notificacion;
import uy.viruscontrol.model.entities.Ubicacion;
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
  			- Notifica sobre nuevos casos, según configuración precargada por el gerente.
  			- Notifica sobre posibles contagios.
 * 
 **/
@Startup
@Singleton
@Local(ApplicationBeanLocal.class)
public class ApplicationBean implements ApplicationBeanLocal {

	private static final long UNA_HORA = 3600000;
	private static final int NCC_SLEEP = 300000;
	private static final int NPC_SLEEP = 900000;
	private static final int MINUTES_TO_FILTER = (NPC_SLEEP + 120000) / 1000 / 60; //siempre serán dos minutos mas que lo que se setea para dormir al thread
	private static final double RADIO_DE_LA_TIERRA = 6371; //radio de la tierra en km
	private static final double COEFICIENTE = 1000; //coeficiente para obtener la distancia en metros

	@EJB private CasoDAOLocal casoDAO;
	@EJB private ServiceAgentProveedorExamenLocal sagProvExamen;
	@EJB private MapaInteractivoBeanLocal mapaBean;
	@EJB private GerenteBeanLocal gerenteBean;
	@EJB private ConfiguracionNotificacionesDAOLocal configNotifDAO;
	@EJB private GerenteDAOLocal gerenteDAO;
	@EJB private UsuarioDAOLocal usuDAO;
	@EJB private ServiceAgentFirebaseLocal saFirebase;
	@EJB private UbicacionDAOLocal daoUbicacion;
	@EJB private CiudadanoBeanLocal beanCiudadano;
	@EJB private EnfermedadDAOLocal daoEnfermedad;
	@EJB private NotificacionDAOLocal daoNotificacion;
	
    public ApplicationBean() {}
    
    @PostConstruct
    public void iniciarAplicacion() {
    	insertMissingToLDAP();
    	
    	initThreadProcesamientoCasos();
    	initThreadNotificacionesCambioCaso();
    	initThreadNotificacionesPosiblesContagios();
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
						Thread.sleep(NCC_SLEEP);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    			}
    		}
    	};

    	th.setDaemon(false);
    	th.start();
    }
    
    private void initThreadNotificacionesPosiblesContagios() {
    	Thread th = new Thread() {
    		public void run() {
    			while (true) {
    				try {
    					notificarPosiblesContagios();
						Thread.sleep(NPC_SLEEP);
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
							caso.setFechaConfirmado(Calendar.getInstance());
							caso.setTipoCaso(TipoCaso.CONFIRMADO);
							update = true;
							break;
						default:
							update = false;
							break;
					}
					if (update) {
						caso.setPathPdfResultadoExamen(resultado.getPathPdf());
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
					gerenteBean.mandarMail(it.getMedico().getCorreo(), asunto, notifCasoMensajeHtml("Dr/a "+it.getMedico().getNombre()+" "+it.getMedico().getApellido(), it.getId(), it.getEnfermedad().getNombre(), it.getTipoCaso().toString()));
				
				if (configuracion.isNotificarGerentes())
					gerenteBean.mandarMail(correosGerentes, asunto, notifCasoMensajeHtml("Gte/a ", it.getId(), it.getEnfermedad().getNombre(), it.getTipoCaso().toString()));
				
				it.setNotificacionEnviada(true);
				casoDAO.merge(it);
			}
		} else
			System.out.println("["+getClass().getCanonicalName()+"] ERROR: No se encontró una configuración de notificaciones para el tipo "+TipoNotificacion.CAMBIOESTADOCASO+". No se puede realizar envío.");
	}
    
    private String notifCasoMensajeHtml(String nombre, int idCaso, String enfermedad, String estado) {
    	return "<p>Estimado/a "+nombre+",</p>"+
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
    	NotificationInfo notificacion = new NotificationInfo(receiverToken, NotificationPriority.high,
											new NotificationInfoData("Resultado de examen disponible", 
																	"El resultado del examen "+idCaso+" es "+estado));
		saFirebase.sendPushNotification(notificacion);
    }
    
    private void notificarCasoEnExposicion(Notificacion notif) {
		// envío notificación push al componente móvil
    	NotificationInfo notificacion = new NotificationInfo(notif.getCaso().getCiudadano().getTokenPushNotifications(), NotificationPriority.high,
											new NotificationInfoData("Posible exposición", 
																	notif.getDescripcion()));
		saFirebase.sendPushNotification(notificacion);
    }
    
    private void notificarPosiblesContagios() {
    	/*
    	 * SOLO ME TRAIGO LOS QUE PUEDEN HABER LLEGADO AL SISTEMA DESDE QUE EL THREAD ESTÁ EN MODO SLEEP.
    	 * PARA MITIGAR ALGUNA PEQUEÑA DIFERENCIA DE TIEMPO, EL LÍMITE A BUSCAR SON EN REALIDAD UNOS
    	 * MINUTOS MAS QUE EL SLEEP DEL THREAD
    	 */
    	Calendar fechaDesde = Calendar.getInstance();
    	fechaDesde.add(Calendar.MINUTE, -1*MINUTES_TO_FILTER);
    	List<Ubicacion> ubicaciones = daoUbicacion.findAllDateFiltered(fechaDesde);
    	//List<Ubicacion> ubicaciones = daoUbicacion.findAll();
    	List<Notificacion> posiblesContagiados = null;
    	if (ubicaciones != null) {
    		HashMap<Integer, Ciudadano> enfermos = beanCiudadano.obtenerCiudadanosEnfermos();
    		for (Ubicacion ub : ubicaciones) {
    			if (enfermos.containsKey(ub.getCiudadano().getIdUsuario())) {
    				posiblesContagiados = analizarUbicacion(ub, ubicaciones);
    				if (posiblesContagiados != null) {
    					for (Notificacion n : posiblesContagiados) {
    						List<Caso> casosCiu = casoDAO.findAllByCiudadano(n.getCaso().getCiudadano().getIdUsuario());
    						boolean notificar = true;
    						for(Caso it : casosCiu) {
    							// si el ciudadano ya fue notificado previamente por este mismo contacto
    							if (it.getId() == n.getCaso().getId() && 
    									it.getCiudadano().getIdUsuario() == n.getCaso().getCiudadano().getIdUsuario()) {
    								notificar = false;
    								break;
    							}
    						}
    						if (notificar) {
	    						notificarCasoEnExposicion(n);
	    						n.getCaso().setId(0);
	    						casoDAO.persist(n.getCaso());
	    						daoNotificacion.merge(n);
	    						System.out.println("Nuevo caso en "+TipoCaso.EXPOSICION+". ID: "+n.getCaso().getId()+".");

    						}
    					}
    				}	
    			}
    		}
    	}
    }
    
    private List<Notificacion> analizarUbicacion(Ubicacion ubicacion, List<Ubicacion> ubicaciones) {
    	/*
    	 *  Fuente de la ecuación para el cálculo de la distancia entre dos puntos en la esfera:
    	 *  https://es.stackoverflow.com/questions/117887/calcular-distancia-entre-dos-puntos-api-google-maps-php
    	 */
    	double radLat1 = Math.toRadians(Double.parseDouble(ubicacion.getLatitud()));
    	double radLon1 = Math.toRadians(Double.parseDouble(ubicacion.getLongitud()));
    	double radLat2 = 0, radLon2 = 0, lonDelta = 0, distancia = 0;//, latDelta = 0;
		List<Notificacion> posiblesContagiados = new ArrayList<Notificacion>();
		List<Caso> casos = casoDAO.findAllConfirmadosByCiudadano(ubicacion.getCiudadano().getIdUsuario());
		
    	for (Ubicacion ub : ubicaciones) {
//    		System.out.println("Procesando: "+ub.getId()+" / Total de ubicaciones: "+ubicaciones.size());
    		// salteo cuando la ubicación coincide
    		if (ub.getId() != ubicacion.getId()) {
	    		radLat2 = Math.toRadians(Double.parseDouble(ub.getLatitud()));
	    		radLon2 = Math.toRadians(Double.parseDouble(ub.getLongitud()));
	    		//latDelta = radLat2 - radLat1;
	    		lonDelta = radLon2 - radLon1;
	    		
	    		// obtengo distancia entre ubicación 2 y 1 utilizando ley esférica de los cosenos
	    		distancia = (RADIO_DE_LA_TIERRA * COEFICIENTE * Math.acos(
	    				Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(lonDelta) +
	    				Math.sin(radLat1) * Math.sin(radLat2)));
	    		
	    		for (Caso c : casos) {
//	    			System.out.println("Distancia calculada: "+distancia+" / Distancia de contagio enfermedad: "+c.getEnfermedad().getDistanciaContagio());
		    		if (distancia <= c.getEnfermedad().getDistanciaContagio()) {
	    				Notificacion n = new Notificacion();
	    				n.setDescripcion(ub.getCiudadano().getNombre() + " " + ub.getCiudadano().getApellido() + " pudo haber estado en contacto con una persona positiva de " + c.getEnfermedad().getNombre());
	    				Caso casoNotif;
						casoNotif = c;
						casoNotif.setFechaConfirmado(null);
	    				casoNotif.setFechaSospechoso(Calendar.getInstance());
	    				casoNotif.setTipoCaso(TipoCaso.EXPOSICION);
	    				casoNotif.setCiudadano(ub.getCiudadano());
						casoNotif.setDepartamento(null);
	    				casoNotif.setExamen(null);
	    				casoNotif.setMedico(null);
	    				casoNotif.setProveedorExamen(null);
	    				casoNotif.setNotificacionEnviada(false);
	    				
	    				n.setCaso(casoNotif);
	    				posiblesContagiados.add(n);
	    				System.out.println(n.getDescripcion());
	    			}
	    		}
    		} //else
    			//System.out.println("Salteando registro. ub "+ub.getId()+" es igual a ubicacion "+ubicacion.getId());
    	}
    	return posiblesContagiados;
    }
}
