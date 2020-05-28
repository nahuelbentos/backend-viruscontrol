package com.prestador.persistence;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.prestador.entities.Ciudadano;


@Stateless
@Local(CiudadanoDAOLocal.class)
public class CiudadanoDAO implements CiudadanoDAOLocal{
	@PersistenceContext(unitName = "com.prestadorsaludPersistenceUnit")
    protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public CiudadanoDAO() {
       
    }
   
	@Override
	public void persist(Ciudadano ciudadano) {
	
		em.persist(ciudadano);
		
	}

	@Override
	public void merge(Ciudadano ciudadano) {
		
		em.merge(ciudadano);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ciudadano> findAll() {

		Query q = em.createQuery("SELECT c FROM Ciudadano c");
		return q.getResultList();
	}

	@Override
	public Ciudadano findById(Integer id) {
		
		return em.find(Ciudadano.class, id);
	}

	@Override
	public void delete(Ciudadano ciudadano) {
		
		em.remove(em.contains(ciudadano) ? ciudadano : em.merge(ciudadano));
		
	}
}
