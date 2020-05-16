package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.NotificacionDAOLocal;
import uy.viruscontrol.model.entities.Notificacion;

/**
 * Session Bean implementation class NotificacionDAO
 */
@Stateless
@LocalBean
public class NotificacionDAO implements NotificacionDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
    /**
     * Default constructor. 
     */
    public NotificacionDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void persist(Notificacion notificacion) {

		em.persist(notificacion);
		
	}

	@Override
	public void merge(Notificacion notificacion) {

		em.merge(notificacion);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notificacion> findAll() {

		Query q = em.createQuery("SELECT n FROM Notificacion");
		return q.getResultList();
	}

	@Override
	public Notificacion findById(Integer id) {
		
		return em.find(Notificacion.class, id);
	}

	@Override
	public void delete(Notificacion notificacion) {

		em.remove(em.contains(notificacion) ? notificacion : em.merge(notificacion));
		
	}

}
