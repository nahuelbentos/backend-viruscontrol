package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.UbicacionDAOLocal;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.Ubicacion;

@Stateless
@LocalBean
public class UbicacionDAO implements UbicacionDAOLocal {
		
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
	public UbicacionDAO() {
        // TODO Auto-generated constructor stub
    }
	
	@Override
	public void persist(Ubicacion ubicacion) {
	
		em.persist(ubicacion);
		
	}
	
	@Override
	public void merge(Ubicacion ubicacion) {
		
		em.merge(ubicacion);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ubicacion> findAll() {

		Query q = em.createQuery("SELECT r FROM Ubicacion r");
		return q.getResultList();
	}
	
	@Override
	public Ubicacion findById(Integer id) {
		
		return em.find(Ubicacion.class, id);
	}
	
	@Override
	public void delete(Ubicacion ubicacion) {
		
		em.remove(em.contains(ubicacion) ? ubicacion : em.merge(ubicacion));
		
	}
	
}
