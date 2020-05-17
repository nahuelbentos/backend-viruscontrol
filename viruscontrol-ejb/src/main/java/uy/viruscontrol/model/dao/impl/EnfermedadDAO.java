package uy.viruscontrol.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.EnfermedadDAOLocal;
import uy.viruscontrol.model.entities.Enfermedad;

/**
 * Session Bean implementation class EnfermedadDAO
 */
@Stateless
@LocalBean
public class EnfermedadDAO implements EnfermedadDAOLocal {

	
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public EnfermedadDAO() {
        // TODO Auto-generated constructor stub
    }

    @Override
	public void persist(Enfermedad enfermedad) {
		
		em.persist(enfermedad);
	}

	@Override
	public void merge(Enfermedad enfermedad) {

		em.merge(enfermedad);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Enfermedad> findAll() {
		
		Query q = em.createQuery("SELECT e FROM Enfermedad e");
		return q.getResultList();
	}

	@Override
	public Enfermedad findById(Integer id) {
		
		return em.find(Enfermedad.class, id);
	}

	@Override
	public void delete(Enfermedad enfermedad) {
		
		em.remove(em.contains(enfermedad) ? enfermedad : em.merge(enfermedad));
	}
	
	@Override
	public boolean exist(String nombre) {
		
		List<Enfermedad> enfermedades = new ArrayList<Enfermedad>(); 
    	enfermedades=findAll();
    	
    	for(Enfermedad enfermedad : enfermedades) {
    		if(enfermedad.getNombre().equals(nombre)) {
    			return true;
    		}
    	}
    	return false;
	}
	
}
