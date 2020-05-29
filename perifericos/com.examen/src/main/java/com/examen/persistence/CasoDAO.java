package com.examen.persistence;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.examen.persistence.CasoDAOLocal;
import com.examen.entities.Caso;

@Stateless
@Local(CasoDAOLocal.class)
public class CasoDAO implements CasoDAOLocal {

	@PersistenceContext(unitName = "com.examenPersistenceUnit")
    protected EntityManager em;
	
    public CasoDAO() {
        super();
    }

	@Override
	public void persist(Caso caso) {

		em.persist(caso);
		
	}

	@Override
	public void merge(Caso caso) {
		
		em.merge(caso);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Caso> findAll() {
		
		Query q = em.createQuery("SELECT c FROM Caso");
		return q.getResultList();
	}

	@Override
	public Caso findById(Integer id) {
		
		return em.find(Caso.class, id);
	}

	@Override
	public void delete(Caso caso) {

		em.remove(em.contains(caso) ? caso : em.merge(caso));
		
		
	}

	
}
