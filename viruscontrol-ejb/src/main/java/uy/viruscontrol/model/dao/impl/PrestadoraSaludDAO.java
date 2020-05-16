package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.PrestadoraSaludDAOLocal;

import uy.viruscontrol.model.entities.PrestadoraSalud;

@Stateless
@LocalBean
public class PrestadoraSaludDAO implements PrestadoraSaludDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
	public PrestadoraSaludDAO() {
		
	}
	
	@Override
	public void persist(PrestadoraSalud prestadoraSalud) {
	
		em.persist(prestadoraSalud);
		
	}
	
	@Override
	public void merge(PrestadoraSalud prestadoraSalud) {

		em.merge(prestadoraSalud);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PrestadoraSalud> findAll() {
		
		Query q = em.createQuery("SELECT p FROM PrestadoraSalud p");
		return q.getResultList();
	}
	
	@Override
	public PrestadoraSalud findById(Integer id) {
		
		return em.find(PrestadoraSalud.class, id);
	}
	
	@Override
	public void delete(PrestadoraSalud prestadoraSalud) {
		
		em.remove(em.contains(prestadoraSalud) ? prestadoraSalud : em.merge(prestadoraSalud));
	}
	
	
	
	
}
