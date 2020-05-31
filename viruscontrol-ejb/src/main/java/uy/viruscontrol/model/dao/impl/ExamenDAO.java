package uy.viruscontrol.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.EnfermedadDAOLocal;
import uy.viruscontrol.model.dao.interfaces.ExamenDAOLocal;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Examen;


@Stateless
@LocalBean
public class ExamenDAO implements ExamenDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
	@EJB private EnfermedadDAOLocal daoEnfermedad;
	
	
	public ExamenDAO() {
		
	}
	
	@Override
	public void persist(Examen examen) {
	
		em.persist(examen);
		
	}
	
	@Override
	public void merge(Examen examen) {
		
		em.merge(examen);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Examen> findAll() {

		Query q = em.createQuery("SELECT e FROM Examen e");
		return q.getResultList();
	}
	
	@Override
	public Examen findById(Integer id) {
		
		return em.find(Examen.class, id);
	}
	
	@Override
	public void delete(Examen examen) {
		
		em.remove(em.contains(examen) ? examen : em.merge(examen));
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Examen> findAllByEnfermedad(int idEnfermedad) {
		Enfermedad enfermedad = daoEnfermedad.findById(idEnfermedad);
		List<Examen> ret = new ArrayList<Examen>();
		if (enfermedad != null) {
			ret = em.createQuery("FROM Examen WHERE enfermedad = :enfermedad")
					.setParameter("enfermedad", enfermedad)
					.getResultList();
		}
		return ret;
	}

}
