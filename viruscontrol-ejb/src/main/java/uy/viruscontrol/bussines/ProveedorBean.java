package uy.viruscontrol.bussines;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import uy.viruscontrol.bussines.interfaces.ProveedorBeanLocal;
import uy.viruscontrol.bussines.interfaces.ProveedorBeanRemote;
import uy.viruscontrol.model.dao.impl.ProveedorRecursoDAO;
import uy.viruscontrol.model.dao.interfaces.ProveedorExamenDAOLocal;
import uy.viruscontrol.model.dao.interfaces.ProveedorRecursoDAOLocal;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.model.entities.ProveedorRecursos;

/**
 * Session Bean implementation class ProveedorBean
 */
@Stateless
@LocalBean
public class ProveedorBean implements ProveedorBeanRemote, ProveedorBeanLocal {

	@EJB ProveedorRecursoDAOLocal daoProveedorRecurso;
	@EJB ProveedorExamenDAOLocal daoProveedorExamen;
    /**
     * Default constructor. 
     */
    public ProveedorBean() {
        // TODO Auto-generated constructor stub
    }
   
    //tipo =1 = proveedor recurso tipo =2 = proveedor Examen
    @Override
    public boolean nuevoProveedor(int tipo,String nombre,String direccion,String barrio,String rangoHorario) {
    	if(tipo==1) {
    		List<ProveedorRecursos> proveedoresRecurso=daoProveedorRecurso.findAll();
    		for(ProveedorRecursos pr:proveedoresRecurso) {
    			if(pr.getNombre().contentEquals(nombre)) {
    				System.out.println("el proveedor de recurso ya existe");
    				return false;
    			}
    		}
    		ProveedorRecursos p=new ProveedorRecursos();
    		p.setBarrio(barrio);
    		p.setDireccion(direccion);
    		p.setNombre(nombre);
    		p.setRangoHorario(rangoHorario);
    		
    		daoProveedorRecurso.persist(p);
    	
    		
    	}else {
    		
    		List<ProveedorExamen> proveedoresExamen=daoProveedorExamen.findAll();
    		for(ProveedorExamen pe:proveedoresExamen) {
    			if(pe.getNombre().contentEquals(nombre)) {
    				System.out.println("el proveedor de examen ya existe");
    				return false;
    			}
    		}
    		ProveedorExamen p=new ProveedorExamen();
    		p.setBarrio(barrio);
    		p.setDireccion(direccion);
    		p.setNombre(nombre);
    		p.setRangoHorario(rangoHorario);
    		daoProveedorExamen.persist(p);
    		
    		
    	}
    	
    	
    	
    	
    	
    	return true;
    	
    }
 
    
    

}
