package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.AdministradorDAOLocal;
import uy.viruscontrol.model.entities.Administrador;


@Stateless
@LocalBean
public class AdministradorDAO implements AdministradorDAOLocal {
	
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
	public AdministradorDAO() {
	
	}
	
	
	@Override
	public void persist(Administrador administrador) {
	
		em.persist(administrador);
		
	}
	
	@Override
	public void merge(Administrador administrador) {
		
		em.merge(administrador);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Administrador> findAll() {

		Query q = em.createQuery("SELECT r FROM Administrador r");
		return q.getResultList();
	}

	@Override
	public Administrador findById(Integer id) {
		
		return em.find(Administrador.class, id);
	}

	@Override
	public void delete(Administrador administrador) {
		
		em.remove(em.contains(administrador) ? administrador : em.merge(administrador));
		
	}
	
	
}
