package uy.viruscontrol.bussines;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import uy.viruscontrol.bussines.interfaces.PrestadorBeanLocal;
import uy.viruscontrol.bussines.interfaces.PrestadorBeanRemote;

import uy.viruscontrol.model.dao.interfaces.PrestadoraSaludDAOLocal;
import uy.viruscontrol.model.entities.PrestadoraSalud;

/**
 * Session Bean implementation class PrestadorBean
 */
@Stateful
@LocalBean
public class PrestadorBean implements PrestadorBeanRemote, PrestadorBeanLocal {

	
	@EJB PrestadoraSaludDAOLocal daoprestadorlocal; 
    /**
     * Default constructor. 
     */
    public PrestadorBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean nuevoPrestador(String nombre) {
    	
    	
    	if(daoprestadorlocal.findAll().isEmpty()) {
    		System.out.println("lista de prestadores vacia");
    		System.out.println("se  creara la prestadora con nomnre:"+nombre);
    		PrestadoraSalud  ps= new PrestadoraSalud();
    		ps.setNombre(nombre);
    		daoprestadorlocal.persist(ps);
    		return true;
    		
    	}else {
    		List<PrestadoraSalud> prestadoras=daoprestadorlocal.findAll();
    		for(PrestadoraSalud p:prestadoras) {
    			if(p.getNombre().contentEquals(nombre)) {
    				System.out.println("prestadora ya existe");
    				return false;
    			}
    		}
    		
    		System.out.println("se  creara la prestadora con nomnre:"+nombre);
    		PrestadoraSalud  ps= new PrestadoraSalud();
    		ps.setNombre(nombre);
    		daoprestadorlocal.persist(ps);
    		return true;
    		
    	}
    	
    }



}
