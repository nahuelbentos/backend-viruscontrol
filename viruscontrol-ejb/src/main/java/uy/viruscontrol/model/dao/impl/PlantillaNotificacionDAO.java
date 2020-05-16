package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.PlantillaNotificacionDAOLocal;
import uy.viruscontrol.model.entities.PlantillaNotificacion;

/**
 * Session Bean implementation class PlantillaNotificacionDAO
 */
@Stateless
@LocalBean
public class PlantillaNotificacionDAO implements PlantillaNotificacionDAOLocal {

	
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
    /**
     * Default constructor. 
     */
    public PlantillaNotificacionDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void persist(PlantillaNotificacion plantillaNotificacion) {


		em.persist(plantillaNotificacion);
		
	}

	@Override
	public void merge(PlantillaNotificacion plantillaNotificacion) {
		
		em.merge(plantillaNotificacion);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlantillaNotificacion> findAll() {
		
		Query q = em.createQuery("SELECT pn FROM PlantillaNotificacion pn");
		return q.getResultList();
	}

	@Override
	public PlantillaNotificacion findById(Integer id) {

		return em.find(PlantillaNotificacion.class, id);
	}

	@Override
	public void delete(PlantillaNotificacion plantillaNotificacion) {
		
		em.remove(em.contains(plantillaNotificacion) ? plantillaNotificacion : em.merge(plantillaNotificacion));
		
	}

}
