package com.examen.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.examen.persistence.EnfermedadDAOLocal;
import com.examen.entities.Enfermedad;

@Stateless
@Local(EnfermedadDAOLocal.class)
public class EnfermedadDAO implements EnfermedadDAOLocal {

	
	@PersistenceContext(unitName = "com.examenPersistenceUnit")
    protected EntityManager em;
	
    public EnfermedadDAO() {
        super();
    }

    @Override
	public void persist(Enfermedad enfermedad) {
		
		em.persist(enfermedad);
	}

	@Override
	public void merge(Enfermedad enfermedad) {

		em.merge(enfermedad);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Enfermedad> findAll() {
		
		Query q = em.createQuery("SELECT e FROM Enfermedad e");
		return q.getResultList();
	}

	@Override
	public Enfermedad findById(Integer id) {
		
		return em.find(Enfermedad.class, id);
	}

	@Override
	public void delete(Enfermedad enfermedad) {
		
		em.remove(em.contains(enfermedad) ? enfermedad : em.merge(enfermedad));
	}
	
	@Override
	public boolean exist(String nombre) {
		
		List<Enfermedad> enfermedades = new ArrayList<Enfermedad>(); 
    	enfermedades=findAll();
    	
    	for(Enfermedad enfermedad : enfermedades) {
    		if(enfermedad.getNombre().equals(nombre)) {
    			return true;
    		}
    	}
    	return false;
	}
	
}
