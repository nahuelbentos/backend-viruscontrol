package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.EstadoExamenDAOLocal;
import uy.viruscontrol.model.entities.EstadoExamen;

/**
 * Session Bean implementation class EstadoExamenDAO
 */
@Stateless
@LocalBean
public class EstadoExamenDAO implements EstadoExamenDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
	protected EntityManager em;

	/**
	 * Default constructor.
	 */
	public EstadoExamenDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(EstadoExamen estadoExamen) {

		em.persist(estadoExamen);

	}

	@Override
	public void merge(EstadoExamen estadoExamen) {

		em.merge(estadoExamen);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoExamen> findAll() {
		
		Query q = em.createQuery("SELECT ee FROM EstadoExamen ee");
		return q.getResultList();
	}

	@Override
	public EstadoExamen findById(Integer id) {
		
		return em.find(EstadoExamen.class, id);
	}

	@Override
	public void delete(EstadoExamen estadoExamen) {

		em.remove(em.contains(estadoExamen) ? estadoExamen : em.merge(estadoExamen));

	}

}
