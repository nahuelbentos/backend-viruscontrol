package uy.viruscontrol.bussines;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import uy.viruscontrol.bussines.interfaces.ProveedorBeanLocal;
import uy.viruscontrol.bussines.interfaces.ProveedorBeanRemote;
import uy.viruscontrol.model.dao.impl.ProveedorRecursoDAO;

/**
 * Session Bean implementation class ProveedorBean
 */
@Stateless
@LocalBean
public class ProveedorBean implements ProveedorBeanRemote, ProveedorBeanLocal {

	@EJB ProveedorRecursoDAO daoProveedorRecurso;
    /**
     * Default constructor. 
     */
    public ProveedorBean() {
        // TODO Auto-generated constructor stub
    }
   
    //tipo =1 = proveedor recurso tipo =2 = proveedor Examen
    @Override
    public boolean nuevoProveedor(int tipo,String Nombre,String direrccion,String barrio,String rangoHorario) {
    	if(tipo==1) {
    		
    		
    		
    		
    		
    	}
    	
    	
    	
    	
    	
    	return true;
    	
    }
 
    
    

}
