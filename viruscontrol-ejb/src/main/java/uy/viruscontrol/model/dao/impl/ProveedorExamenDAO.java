package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.ProveedorExamenDAOLocal;
import uy.viruscontrol.model.entities.ProveedorExamen;



@Stateless
@LocalBean
public class ProveedorExamenDAO implements ProveedorExamenDAOLocal {
	
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
	public ProveedorExamenDAO() {
		
	}
	@Override
	public void persist(ProveedorExamen proveedorExamen) {
	
		em.persist(proveedorExamen);
		
	}
	
	@Override
	public void merge(ProveedorExamen proveedorExamen) {
		
		em.merge(proveedorExamen);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProveedorExamen> findAll() {

		Query q = em.createQuery("SELECT p FROM ProveedorExamen p");
		return q.getResultList();
	}
	
	@Override
	public ProveedorExamen findById(Integer id) {
		
		return em.find(ProveedorExamen.class, id);
	}

	@Override
	public void delete(ProveedorExamen proveedorExamen) {
		
		em.remove(em.contains(proveedorExamen) ? proveedorExamen : em.merge(proveedorExamen));
		
	}


}
