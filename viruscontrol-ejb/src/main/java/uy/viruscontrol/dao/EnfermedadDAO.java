package uy.viruscontrol.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import uy.viruscontrol.entities.Enfermedad;

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
	
	
}
