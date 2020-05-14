package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.RecursoDAOLocal;
import uy.viruscontrol.model.entities.Recurso;

/**
 * Session Bean implementation class RecursoDAO
 */
@Stateless
@LocalBean
public class RecursoDAO implements RecursoDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public RecursoDAO() {
        // TODO Auto-generated constructor stub
    }
   
	@Override
	public void persist(Recurso recurso) {
	
		em.persist(recurso);
		
	}

	@Override
	public void merge(Recurso recurso) {
		
		em.merge(recurso);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recurso> findAll() {

		Query q = em.createQuery("SELECT r FROM Recurso r");
		return q.getResultList();
	}

	@Override
	public Recurso findById(Integer id) {
		
		return em.find(Recurso.class, id);
	}

	@Override
	public void delete(Recurso recurso) {
		
		em.remove(em.contains(recurso) ? recurso : em.merge(recurso));
		
	}
    
    

}
