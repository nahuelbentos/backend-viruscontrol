package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import uy.viruscontrol.model.dao.interfaces.VisitaMedicoDAOLocal;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.VisitaMedico;

@Stateless
@LocalBean
public class VisitaMedicoDAO implements VisitaMedicoDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
	public VisitaMedicoDAO() {}
	
	@Override
	public void persist(VisitaMedico visitaMedico) throws PersistenceException {
		try {
			em.persist(visitaMedico);
		} catch (RollbackException e) {
			throw new PersistenceException("Clave duplicada");
		}
	}
	
	@Override
	public void merge(VisitaMedico visitaMedico) {
		
		em.merge(visitaMedico);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VisitaMedico> findAll() {

		Query q = em.createQuery("SELECT v FROM VisitaMedico v");
		return q.getResultList();
	}

	
	@Override
	public VisitaMedico findById(Integer id) {
		
		return em.find(VisitaMedico.class, id);
	}
	
	@Override
	public void delete(VisitaMedico visitaMedico) {
		
		em.remove(em.contains(visitaMedico) ? visitaMedico : em.merge(visitaMedico));
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VisitaMedico> findByMedico(Medico m) { 
		
		List<VisitaMedico> visitas = em.createQuery("SELECT v FROM VisitaMedico v WHERE medico = :m")
				.setParameter("m", m)
				.getResultList();
		return visitas;
	}
	
	
	
	
}
