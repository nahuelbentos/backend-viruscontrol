package uy.viruscontrol.bussines;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.recurso.model.entities.ProveedorRecurso;

import uy.viruscontrol.bussines.interfaces.CiudadanoBeanLocal;
import uy.viruscontrol.bussines.interfaces.GerenteBeanLocal;
import uy.viruscontrol.bussines.interfaces.SuscripcionBeanLocal;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorRecurso;
import uy.viruscontrol.model.dao.interfaces.RecursoProveedorDAOLocal;
import uy.viruscontrol.model.dao.interfaces.SuscripcionDAOLocal;
import uy.viruscontrol.model.entities.ProveedorRecursos;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.RecursoProveedor;
import uy.viruscontrol.model.entities.Suscripcion;

/**
 * Session Bean implementation class SuscripcionBean
 */
@Singleton
@LocalBean
@Startup
public class SuscripcionBean implements SuscripcionBeanLocal {
	
	@EJB private SuscripcionDAOLocal daoSuscripcion;
	@EJB private ServiceAgentProveedorRecurso sa;
	@EJB private GerenteBeanLocal gerenteBean;
	@EJB private SuscripcionDAOLocal suscripcionDao;
	@EJB private RecursoProveedorDAOLocal rpDao;
	
	
	
    public SuscripcionBean() {
        // TODO Auto-generated constructor stub
    }
    
    @PostConstruct
    public void iniciarAplicacion() {
    	Thread th = new Thread() {
    		public void run() {
    			
    			while (true) {
    				try {
    					
    					List<Suscripcion> suscripciones=daoSuscripcion.findAll();
    					if(suscripciones.isEmpty()) {
    						System.out.println("suscripciones esta vacia");
    					}
    					for(Suscripcion s:suscripciones) {		//recorro suscripciones
    						
    						System.out.println("s mail: "+s.getCiudadano().getCorreo());
    						System.out.println("s barrio: "+s.getBarrio());
    					if(s.isNotificado()==false) {
    						
    						boolean notifique=false;
    						List<ProveedorRecursos> prs=sa.getProveedoresPeriferico(); //traigo los proveedores del periferico
    						if (prs.isEmpty()) {
    							//System.out.println("lista prov vacia");
    						}
    					//System.out.println("cantidad de proveedores"+prs.size());
    						
    						for(ProveedorRecursos provR:prs) {//recorro los proveedores del periferico
    						//	System.out.println("proveedor nombre: "+provR.getNombre());
    							//System.out.println("prov codigo periferico "+provR.getCodigoPeriferico());
    							
    							if(provR.getBarrio().contentEquals(s.getBarrio())) {	//me fijo si barrio del periferico es el barrio de la suscripcion
    								//System.out.println("el proveedor "+provR.getNombre() +"es del barrio de la suscripcion");
    								List<Recurso> recProv=sa.getRecursosProvPeriferico(provR.getCodigoPeriferico());//traigo los recursos del proveedor
    								for(Recurso r:recProv) {//recorro los recursos del proveedor
    									
    									//System.out.println("nombre recurso del proveedor "+r.getNombre());
    									
    									//System.out.println("stock del recurso "+sa.getStockDisponible(provR.getCodigoPeriferico(),r.getCodigoPeriferico()));
    									if(r.getNombre().contentEquals(s.getRecurso())) {
    										//System.out.println("el recurso de la suscripcion esta en el proveedor del barrio");
    										//System.out.println("se mandara mail a "+s.getCiudadano().getCorreo());
    										
    										System.out.println("stock del recurso "+sa.getStockDisponible(provR.getCodigoPeriferico(),r.getCodigoPeriferico()));
    										if(sa.getStockDisponible(provR.getCodigoPeriferico(),r.getCodigoPeriferico())>0) {//me fijo que haya stock del recurso
        										System.out.println("el recurso esta disponible en el barrio de la suscripcion");
    											gerenteBean.mandarMail(s.getCiudadano().getCorreo(), "Disponibilidad de Recurso", "Somos de VirusControlUY le comunicamos que hay disponibilidad del recurso: "+r.getNombre()+" en el proveedor: "+provR.getNombre()+" Stock:"+sa.getStockDisponible(provR.getCodigoPeriferico(),r.getCodigoPeriferico()));
    											notifique=true;
        									}
    									}
    									
    									
    								}
    								
    								
    								
    								
    							}
    						}
    					//aca
    						if(notifique==true) {
    							s.setNotificado(true);
    							daoSuscripcion.merge(s);
    						}else {
    							System.out.println("Verificando baselocal");
    							List<RecursoProveedor> rps=rpDao.findAll();
    							for(RecursoProveedor rp:rps) {
    								if(rp.getProveedor().getBarrio().contentEquals(s.getBarrio())) {
    									if(rp.getRecurso().getNombre().contentEquals(s.getRecurso())) {
    										if(rp.getCantidad()>0) {
    											System.out.println("el recurso esta disponible en el proveedor del barrio");
    											System.out.println("se procedera a mandar el mail");
    											gerenteBean.mandarMail(s.getCiudadano().getCorreo(), "Disponibilidad de Recurso", "Somos de VirusControlUY le comunicamos que hay disponibilidad del recurso: "+rp.getRecurso().getNombre()+" en el proveedor: "+rp.getProveedor().getNombre()+" Stock: "+rp.getCantidad());
    											s.setNotificado(true);
    											daoSuscripcion.merge(s);
    										}
    									}
    								}
    							}
    							
    							
    							
    						}
    						
    					}
    					
    					
    					
    					}
    					
    					System.out.println("el hilo de suscripcion esta corriendo");
						Thread.sleep(300000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    			}
    		}
    	};
    	
    	th.setDaemon(false);
    	th.start();
    }

}
