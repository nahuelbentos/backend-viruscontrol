package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.DepartamentoDAOLocal;
import uy.viruscontrol.model.entities.Departamento;

@Stateless
@LocalBean
public class DepartamentoDAO implements DepartamentoDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
	
	
	public DepartamentoDAO() {
		
	}
	
	@Override
	public void persist(Departamento departamento) {
	
		em.persist(departamento);
		
	}
	
	@Override
	public void merge(Departamento departamento) {

		em.merge(departamento);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Departamento> findAll() {
		
		Query q = em.createQuery("SELECT e FROM Departamento e");
		return q.getResultList();
	}
	
	@Override
	public Departamento findById(Integer id) {
		
		return em.find(Departamento.class, id);
	}
	
	@Override
	public void delete(Departamento departamento) {
		
		em.remove(em.contains(departamento) ? departamento : em.merge(departamento));
	}
	
	
}
