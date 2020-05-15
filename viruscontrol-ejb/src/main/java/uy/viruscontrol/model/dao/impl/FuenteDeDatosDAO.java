package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.FuenteDeDatosDAOLocal;
import uy.viruscontrol.model.entities.FuenteDeDatos;


@Stateless
@LocalBean
public class FuenteDeDatosDAO implements FuenteDeDatosDAOLocal {
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;

	public FuenteDeDatosDAO() {
		
	}
	
	@Override
	public void persist(FuenteDeDatos fuente) {
	
		em.persist(fuente);
		
	}
	
	@Override
	public void merge(FuenteDeDatos fuente) {
		
		em.merge(fuente);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FuenteDeDatos> findAll() {

		Query q = em.createQuery("SELECT r FROM FuenteDeDatos r");
		return q.getResultList();
	}
	
	@Override
	public FuenteDeDatos findById(int id) {
		
		return em.find(FuenteDeDatos.class, id);
	}

	@Override
	public void delete(FuenteDeDatos fuente) {
		
		em.remove(em.contains(fuente) ? fuente : em.merge(fuente));
		
	}
    
	
	
}
