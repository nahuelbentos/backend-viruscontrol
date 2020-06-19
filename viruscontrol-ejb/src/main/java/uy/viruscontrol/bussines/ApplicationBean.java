package uy.viruscontrol.bussines;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import uy.viruscontrol.bussines.enumerated.TipoCaso;
import uy.viruscontrol.bussines.interfaces.ApplicationBeanLocal;
import uy.viruscontrol.bussines.map.MapaInteractivoBeanLocal;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorExamenLocal;
import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.UsuarioDAOLocal;
import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Caso;
import uy.viruscontrol.model.entities.EstadoExamen;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.ldap.LDAPConexion;

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
 * 
 **/
@Singleton
@LocalBean
@Startup
public class ApplicationBean implements ApplicationBeanLocal {

	private static final long UNA_HORA = 3600000;

	@EJB private CasoDAOLocal casoDAO;
	@EJB private ServiceAgentProveedorExamenLocal sagProvExamen;
	@EJB private MapaInteractivoBeanLocal mapaBean;
	@EJB private UsuarioDAOLocal usuDAO;
	
    public ApplicationBean() {}
    
    @PostConstruct
    public void iniciarAplicacion() {
    	
    	insertMissingToLDAP();
    	
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
					EstadoExamen estado = sagProvExamen.obtenerResultadoExamen(caso.getId());
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
					if (update)
						casoDAO.merge(caso);
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

}
