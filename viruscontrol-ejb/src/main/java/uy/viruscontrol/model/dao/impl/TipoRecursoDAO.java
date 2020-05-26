package uy.viruscontrol.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.TipoRecursoDAOLocal;
import uy.viruscontrol.model.entities.TipoRecurso;

@Stateless
@LocalBean
public class TipoRecursoDAO implements TipoRecursoDAOLocal{

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;

	public TipoRecursoDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void persist(TipoRecurso tipoRecurso) {
		
		em.persist(tipoRecurso);
		
	}

	@Override
	public void merge(TipoRecurso tipoRecurso) {
		
		em.merge(tipoRecurso);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoRecurso> findAll() {

		Query q = em.createQuery("SELECT tr FROM TipoRecurso tr");
		return q.getResultList();
	}

	@Override
	public TipoRecurso findById(Integer id) {
		
		return em.find(TipoRecurso.class, id);
	}

	@Override
	public void delete(TipoRecurso tipoRecurso) {
		
		em.remove(em.contains(tipoRecurso) ? tipoRecurso : em.merge(tipoRecurso));
		
	}
    
	@Override
	public boolean exist(String nombre) {
		
		List<TipoRecurso> tipoRecursos = new ArrayList<TipoRecurso>(); 
		tipoRecursos=findAll();
    	
    	for(TipoRecurso tipoRecurso : tipoRecursos) {
    		if(tipoRecurso.getNombre().equals(nombre)) {
    			return true;
    		}
    	}
    	return false;
	}
}
