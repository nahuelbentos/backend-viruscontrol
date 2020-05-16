package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.GerenteDAOLocal;
import uy.viruscontrol.model.entities.Gerente;


@Stateless
@LocalBean
public class GerenteDAO implements GerenteDAOLocal {
	
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
	public GerenteDAO() {
		
	}
	
	@Override
	public void persist(Gerente gerente) {
	
		em.persist(gerente);
		
	}

	@Override
	public void merge(Gerente gerente) {
		
		em.merge(gerente);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gerente> findAll() {

		Query q = em.createQuery("SELECT g FROM Gerente g");
		return q.getResultList();
	}

	@Override
	public Gerente findById(Integer id) {
		
		return em.find(Gerente.class, id);
	}

	@Override
	public void delete(Gerente gerente) {
		
		em.remove(em.contains(gerente) ? gerente : em.merge(gerente));
		
	}
    
	
}
