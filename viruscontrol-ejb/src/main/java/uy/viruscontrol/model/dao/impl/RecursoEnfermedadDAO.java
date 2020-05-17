package uy.viruscontrol.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.RecursoEnfermedadDAOLocal;
import uy.viruscontrol.model.entities.IdRecursoEnfermedad;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.RecursoEnfermedad;

/**
 * Session Bean implementation class RecursoEnfermedadDAO
 */
@Stateless
@LocalBean
public class RecursoEnfermedadDAO implements RecursoEnfermedadDAOLocal {
	
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
    /**
     * Default constructor. 
     */
    public RecursoEnfermedadDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void persist(RecursoEnfermedad recursoEnfermedad) {
		em.persist(recursoEnfermedad);
		
	}
	
	@Override
	public void merge(RecursoEnfermedad recursoEnfermedad) {
		
		em.merge(recursoEnfermedad);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RecursoEnfermedad> findAll() {
		Query q = em.createQuery("SELECT re FROM RecursoEnfermedad");
		return q.getResultList();
	}

	@Override
	public RecursoEnfermedad findById(Integer id) {
		
		return em.find(RecursoEnfermedad.class, id);
	}

	@Override
	public void delete(RecursoEnfermedad recursoEnfermedad) {

		em.remove(em.contains(recursoEnfermedad) ? recursoEnfermedad : em.merge(recursoEnfermedad));
		
	}
/*
	@Override
	public boolean exist(RecursoEnfermedad recursoEnfermedad) {
		
		IdRecursoEnfermedad idAux = em.find(IdRecursoEnfermedad.class, recursoEnfermedad.getId());
		if(idAux == null)
			return false;
		else
			return true;
	}
*/
}
