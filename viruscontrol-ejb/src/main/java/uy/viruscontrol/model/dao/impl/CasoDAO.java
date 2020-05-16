package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.entities.Caso;

/**
 * Session Bean implementation class CasoDAO
 */
@Stateless
@LocalBean
public class CasoDAO implements CasoDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
    /**
     * Default constructor. 
     */
    public CasoDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void persist(Caso caso) {

		em.persist(caso);
		
	}

	@Override
	public void merge(Caso caso) {
		
		em.merge(caso);
		
	}

	@Override
	public List<Caso> findAll() {
		
		Query q = em.createQuery("SELECT c FROM Caso");
		return q.getResultList();
	}

	@Override
	public Caso findById(Integer id) {
		
		return em.find(Caso.class, id);
	}

	@Override
	public void delete(Caso caso) {

		em.remove(em.contains(caso) ? caso : em.merge(caso));
		
		
	}

	
}
