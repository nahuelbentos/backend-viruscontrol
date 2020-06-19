package uy.viruscontrol.model.dao.impl;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.SuscripcionDAOLocal;

import uy.viruscontrol.model.entities.Suscripcion;

@Stateless
@LocalBean
public class SuscripcionDAO implements SuscripcionDAOLocal{
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;

	public SuscripcionDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void persist(Suscripcion suscripcion) {
		
		em.persist(suscripcion);
		
	}

	@Override
	public void merge(Suscripcion suscripcion) {
		
		em.merge(suscripcion);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Suscripcion> findAll() {
		
		Query q = em.createQuery("SELECT s FROM Suscripcion s");
		return q.getResultList();
	}

	@Override
	public Suscripcion findById(Integer id) {

		return em.find(Suscripcion.class, id);
	}

	@Override
	public void delete(Suscripcion suscripcion) {

		em.remove(em.contains(suscripcion) ? suscripcion : em.merge(suscripcion));
		
	}
    


}
