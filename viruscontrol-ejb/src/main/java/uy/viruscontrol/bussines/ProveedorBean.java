package uy.viruscontrol.bussines;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import uy.viruscontrol.bussines.interfaces.ProveedorBeanLocal;
import uy.viruscontrol.bussines.interfaces.ProveedorBeanRemote;
import uy.viruscontrol.model.dao.interfaces.ProveedorExamenDAOLocal;
import uy.viruscontrol.model.dao.interfaces.ProveedorRecursoDAOLocal;
import uy.viruscontrol.model.entities.PrestadoraSalud;
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
    
    //Obtener proveedores Auxiliar
    public List<ProveedorRecursos> obtenerProveedoresRecursos(){
    	List<ProveedorRecursos> proveedoresRecursos = daoProveedorRecurso.findAll();
    	List<ProveedorRecursos> proveedoresRecursosNoEliminados = new ArrayList<ProveedorRecursos>();
    	for(ProveedorRecursos pr : proveedoresRecursos)
    		if(!pr.isDeleted())
    			proveedoresRecursosNoEliminados.add(pr);
    	
    	return (proveedoresRecursosNoEliminados != null) ? proveedoresRecursosNoEliminados : new ArrayList<ProveedorRecursos>();
    }
    public List<ProveedorExamen> obtenerProveedoresExamenes(){
    	
    	List<ProveedorExamen> proveedoresExamenes = daoProveedorExamen.findAll();
    	List<ProveedorExamen> proveedoresExamenNoEliminados = new ArrayList<ProveedorExamen>();
    	for(ProveedorExamen pr : proveedoresExamenes)
    		if(!pr.isDeleted())
    			proveedoresExamenNoEliminados.add(pr);
    	
    	return (proveedoresExamenNoEliminados != null) ? proveedoresExamenNoEliminados : new ArrayList<ProveedorExamen>();
    }
 
   
    @Override
	public boolean actualizarProveedorExamen(ProveedorExamen proveedorExamen) {
		
    	ProveedorExamen pe = new ProveedorExamen();
		pe=daoProveedorExamen.findById(proveedorExamen.getId());
		
		if(pe != null) {
			
			pe.setNombre(proveedorExamen.getNombre());
			pe.setDireccion(proveedorExamen.getDireccion());
			pe.setBarrio(proveedorExamen.getBarrio());
			pe.setRangoHorario(proveedorExamen.getRangoHorario());
			
			daoProveedorExamen.merge(pe);
			System.out.println("Proveedor de Examen actualizado correctamente.");
			return true;
		}else {
			System.out.println("Error, el Proveedor de Examen no existe.");
			return false;
		}
	}
    
    @Override
	public boolean actualizarProveedorRecursos(ProveedorRecursos proveedorRecurso) {
		
    	ProveedorRecursos pr = new ProveedorRecursos();
		pr=daoProveedorRecurso.findById(proveedorRecurso.getId());
		
		if(pr != null) {
			
			pr.setNombre(proveedorRecurso.getNombre());
			pr.setDireccion(proveedorRecurso.getDireccion());
			pr.setBarrio(proveedorRecurso.getBarrio());
			pr.setRangoHorario(proveedorRecurso.getRangoHorario());
			
			daoProveedorRecurso.merge(pr);
			System.out.println("Proveedor de Recurso actualizado correctamente.");
			return true;
		}else {
			System.out.println("Error, el Proveedor de Recurso no existe.");
			return false;
		}
	}
    
    @Override
    public boolean eliminarProveedorRecursos(ProveedorRecursos proveedorRecurso) {
    	ProveedorRecursos pr = new ProveedorRecursos();
		pr=daoProveedorRecurso.findById(proveedorRecurso.getId());

		if(pr != null) {
			
			pr.setDeleted(true);
			daoProveedorRecurso.merge(pr);
			
			System.out.println("Proveedor de Recurso eliminado.");
			return true;
		}else {
			System.out.println("Error, el Proveedor de Recurso no existe.");
			return false;
		}
    }
    
    @Override
    public boolean eliminarProveedorExamenes(ProveedorExamen proveedorExamen) {
    	ProveedorExamen pr = new ProveedorExamen();
		pr=daoProveedorExamen.findById(proveedorExamen.getId());

		if(pr != null) {
			
			pr.setDeleted(true);
			daoProveedorExamen.merge(pr);
			
			System.out.println("Proveedor de Recurso eliminado.");
			return true;
		}else {
			System.out.println("Error, el Proveedor de Recurso no existe.");
			return false;
		}
    }

}
