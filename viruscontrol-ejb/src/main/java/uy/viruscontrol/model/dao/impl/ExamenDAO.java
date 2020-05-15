package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.ExamenDAOLocal;
import uy.viruscontrol.model.entities.Examen;
import uy.viruscontrol.model.entities.Recurso;


@Stateless
@LocalBean
public class ExamenDAO implements ExamenDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
	
	public ExamenDAO() {
		
	}
	
	@Override
	public void persist(Examen examen) {
	
		em.persist(examen);
		
	}
	
	@Override
	public void merge(Examen examen) {
		
		em.merge(examen);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Examen> findAll() {

		Query q = em.createQuery("SELECT r FROM Examen r");
		return q.getResultList();
	}
	
	@Override
	public Examen findById(Integer id) {
		
		return em.find(Examen.class, id);
	}
	
	@Override
	public void delete(Examen examen) {
		
		em.remove(em.contains(examen) ? examen : em.merge(examen));
		
	}

}
