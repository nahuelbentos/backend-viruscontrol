package uy.viruscontrol.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.entities.TipoEnfermedad;

/**
 * Session Bean implementation class TipoEnfermedadDAO
 */
@Stateless
@LocalBean
public class TipoEnfermedadDAO implements TipoEnfermedadDAOLocal {

	
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
    
	
    
    public TipoEnfermedadDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
    public void persist(TipoEnfermedad tipoEnfermedad) {
		
		em.persist(tipoEnfermedad);
	}

    @Override
	public void merge(TipoEnfermedad tipoEnfermedad) {

		em.merge(tipoEnfermedad);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TipoEnfermedad> findAll() {
		
		Query q = em.createQuery("SELECT te FROM TipoEnfermedad te");
		return q.getResultList();
	}

	@Override
	public TipoEnfermedad findById(Integer id) {
		
		return em.find(TipoEnfermedad.class, id);
	}

	@Override
	public void delete(TipoEnfermedad tipoEnfermedad) {
		
		em.remove(em.contains(tipoEnfermedad) ? tipoEnfermedad : em.merge(tipoEnfermedad));
	}

}
