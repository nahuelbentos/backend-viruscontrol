package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uy.viruscontrol.model.dao.interfaces.RecursoProveedorDAOLocal;
import uy.viruscontrol.model.entities.IdRecursoProveedor;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.RecursoProveedor;

@Stateless
@LocalBean
public class RecursoProveedorDAO implements RecursoProveedorDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
    public RecursoProveedorDAO() {
    	super();
    }

	@Override
	public void persist(RecursoProveedor recProv) {
		em.persist(recProv);
	}

	@Override
	public void merge(RecursoProveedor recProv) {
		em.merge(recProv);
	}

	@Override
	public List<RecursoProveedor> findAll() {
		@SuppressWarnings("unchecked")
		List<RecursoProveedor> lista = em.createQuery("FROM RecursoProveedor").getResultList();
		return lista;
	}

	@Override
	public RecursoProveedor findById(IdRecursoProveedor id) {
		return em.find(RecursoProveedor.class, id);
	}

	@Override
	public void delete(IdRecursoProveedor id) {
		em.remove(em.contains(id) ? id : em.merge(id));
	}

	@Override
	public List<RecursoProveedor> findAllByRecurso(Recurso recurso) {
		@SuppressWarnings("unchecked")
		List<RecursoProveedor> lista = em.createQuery("FROM RecursoProveedor WHERE recurso = :recurso")
										.setParameter(":recurso", recurso)
										.getResultList();
		return lista;
	}

	
}
