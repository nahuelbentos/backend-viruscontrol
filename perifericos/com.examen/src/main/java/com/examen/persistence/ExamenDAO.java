package com.examen.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.examen.persistence.EnfermedadDAOLocal;
import com.examen.persistence.ExamenDAOLocal;
import com.examen.entities.Enfermedad;
import com.examen.entities.Examen;


@Stateless
@Local(ExamenDAOLocal.class)
public class ExamenDAO implements ExamenDAOLocal {

	@PersistenceContext(unitName = "com.examenPersistenceUnit")
    protected EntityManager em;
	
	@EJB private EnfermedadDAOLocal daoEnfermedad;
	
	
	public ExamenDAO() {
		super();
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
