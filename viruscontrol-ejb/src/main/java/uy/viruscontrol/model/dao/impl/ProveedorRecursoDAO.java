package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.ProveedorRecursoDAOLocal;
import uy.viruscontrol.model.entities.Proveedor;
import uy.viruscontrol.model.entities.ProveedorRecursos;

@Stateless
@LocalBean
public class ProveedorRecursoDAO implements ProveedorRecursoDAOLocal {
	
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ProveedorRecursoDAO() {
     
    }
   
	@Override
	public void persist(ProveedorRecursos proveedorRecursos) {
	
		em.persist(proveedorRecursos);
		
	}

	@Override
	public void merge(ProveedorRecursos proveedorRecursos) {
		
		em.merge(proveedorRecursos);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProveedorRecursos> findAll() {

		Query q = em.createQuery("SELECT p FROM ProveedorRecursos p");
		return q.getResultList();
	}

	@Override
	public ProveedorRecursos findById(Integer id) {
		
		return em.find(ProveedorRecursos.class, id);
	}

	@Override
	public void delete(ProveedorRecursos proveedorRecursos) {
		
		em.remove(em.contains(proveedorRecursos) ? proveedorRecursos : em.merge(proveedorRecursos));
		
	}
	
	@SuppressWarnings("unchecked")
	@Override 
	public ProveedorRecursos findByExternalId(String codigo) {
		List<ProveedorRecursos> l = em.createQuery("FROM ProveedorRecursos WHERE codigoPeriferico = :codigo")
										.setParameter("codigo", codigo)
										.getResultList();
		
		for (Proveedor it : l) {
			if (it instanceof ProveedorRecursos)
				return (ProveedorRecursos) it;
		}
		return null;
	}

}
