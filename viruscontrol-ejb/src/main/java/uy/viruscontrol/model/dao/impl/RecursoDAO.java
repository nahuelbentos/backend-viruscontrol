package uy.viruscontrol.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.RecursoDAOLocal;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.TipoRecurso;


/**
 * Session Bean implementation class RecursoDAO
 */
@Stateless
@LocalBean
public class RecursoDAO implements RecursoDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public RecursoDAO() {
        // TODO Auto-generated constructor stub
    }
   
	@Override
	public void persist(Recurso recurso) {
		
		em.persist(recurso);
		
	}

	@Override
	public void merge(Recurso recurso) {
		
		em.merge(recurso);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recurso> findAll() {

		Query q = em.createQuery("SELECT r FROM Recurso r");
		return q.getResultList();
	}

	@Override
	public Recurso findById(Integer id) {
		
		return em.find(Recurso.class, id);
	}

	@Override
	public void delete(Recurso recurso) {
		
		em.remove(em.contains(recurso) ? recurso : em.merge(recurso));
		
	}
    
	@Override
	public boolean exist(String nombre) {
		
		List<Recurso> recursos = new ArrayList<Recurso>(); 
		recursos=findAll();
    	
    	for(Recurso recurso : recursos) {
    		if(recurso.getNombre().equals(nombre)) {
    			return true;
    		}
    	}
    	return false;
	}
	
	
	@Override
	public List<Recurso> findRecursoByTipoRecurso(TipoRecurso tipoRecurso) {
		/*
		@SuppressWarnings("unchecked")
		List<Recurso> lista = em.createQuery("FROM Recurso r WHERE r.tipoRecurso = :tipoRecurso")
				.setParameter("tipoRecurso", tipoRecurso)
				.getResultList();
		return lista;
	}
	*/	List<Recurso> listaRecursosPorTipo = new ArrayList<Recurso>();
		List<Recurso> listaRecursos = findAll();
		for(Recurso lr : listaRecursos) {
			if(lr.getTipoRecurso().getId() == tipoRecurso.getId()) {
				Recurso recAux = new Recurso();
				recAux.setId(lr.getId());
				recAux.setNombre(lr.getNombre());
				recAux.setTipoRecurso(lr.getTipoRecurso());
				listaRecursosPorTipo.add(recAux);
			}
		}
		
		return listaRecursosPorTipo;
	}
	
	
	@Override
	public List<Recurso> getAllRecursos() {
		
		List<Recurso> listaRecursosDisponibles = new ArrayList<Recurso>();
		//listaRecursosDisponibles = em.createQuery("SELECT r.id, r.nombre, r.tipoRecurso FROM Recurso r").getResultList();
		
		List<Recurso> listaRecursos = findAll();
		for(Recurso lr : listaRecursos) {
			
			Recurso recAux = new Recurso();
			recAux.setId(lr.getId());
			recAux.setNombre(lr.getNombre());
			recAux.setTipoRecurso(lr.getTipoRecurso());
			listaRecursosDisponibles.add(recAux);
			
		}
		
		return listaRecursosDisponibles;
	}
	
}	
