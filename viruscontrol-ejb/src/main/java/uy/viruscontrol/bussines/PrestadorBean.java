package uy.viruscontrol.bussines;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import uy.viruscontrol.bussines.interfaces.PrestadorBeanLocal;
import uy.viruscontrol.bussines.interfaces.PrestadorBeanRemote;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentPrestadoraSaludLocal;
import uy.viruscontrol.model.dao.interfaces.CiudadanoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.MedicoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.PrestadoraSaludDAOLocal;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.PrestadoraSalud;

/**
 * Session Bean implementation class PrestadorBean
 */
@Stateful
@LocalBean
public class PrestadorBean implements PrestadorBeanRemote, PrestadorBeanLocal {

	
	@EJB PrestadoraSaludDAOLocal daoprestadorlocal;
	@EJB private MedicoDAOLocal daoMedico;
	@EJB private CiudadanoDAOLocal daoCiudadano;
	@EJB private ServiceAgentPrestadoraSaludLocal saRucaf;
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

	@Override
	public List<Medico> obtenerMedicosVisita(int idCiudadano) {
		try {
			PrestadoraSalud p = saRucaf.obtenerPrestadorDeSalud(idCiudadano);
			List<Medico> medicos = null;
			
			if (saRucaf.estaDisponible(p.getId())) {
				medicos = daoMedico.findAllByPrestadoraSalud(p);
			} else {
				medicos = daoMedico.findAllByPrestadoraSalud(saRucaf.obtenerPrestadorDeSaludAlternativo(idCiudadano));
			}
			
			return (medicos != null) ? medicos : new ArrayList<Medico>();
		} catch (Exception e) {
			// puede entrar por nullpointer exeption
			return new ArrayList<Medico>();
		}
		
	}

	@Override
	public boolean actualizarPrestador(PrestadoraSalud prestadoraSalud) {
		
		PrestadoraSalud ps = new PrestadoraSalud();
		ps=daoprestadorlocal.findById(prestadoraSalud.getId());
		
		if(ps != null) {
			ps.setNombre(prestadoraSalud.getNombre());
			daoprestadorlocal.merge(ps);
			System.out.println("Prestadora de Salud actualizada correctamente.");
			return true;
		}else {
			System.out.println("Error, la Prestadora de Salud no existe.");
			return false;
		}
	}

		
	  //Auxiliar que retorna el id de un Prestador dado su nombre
	    public int getIdPrestadorByName(String nombrePrestadoraSalud) {
	    	
				int id = 0;
		    	List<PrestadoraSalud> prestadoras = new ArrayList<PrestadoraSalud>(); 
		    	prestadoras=daoprestadorlocal.findAll();
		    	
		    	for(PrestadoraSalud prestadora : prestadoras) {
		    		if(prestadora.getNombre().equals(nombrePrestadoraSalud)) {
		    			id=prestadora.getId();
		    			break;
		    		}
		    	}
		    	
		    	return id;
	    }

	    @Override
	    public List<PrestadoraSalud> obtenerPrestadorasSalud(){
	    	
	    	List<PrestadoraSalud> prestadorasSalud = daoprestadorlocal.findAll();
	    	return (prestadorasSalud != null) ? prestadorasSalud : new ArrayList<PrestadoraSalud>();
	    	
	    }


}
