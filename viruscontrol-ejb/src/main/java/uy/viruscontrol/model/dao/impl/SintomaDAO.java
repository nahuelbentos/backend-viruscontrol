package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.SintomaDAOLocal;
import uy.viruscontrol.model.entities.Sintoma;

/**
 * Session Bean implementation class Sintoma
 */
@Stateless
@LocalBean
public class SintomaDAO implements SintomaDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;

	public SintomaDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void persist(Sintoma sintoma) {
		
		em.persist(sintoma);
		
	}

	@Override
	public void merge(Sintoma sintoma) {
		
		em.merge(sintoma);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sintoma> findAll() {
		
		Query q = em.createQuery("SELECT s FROM Sintoma s");
		return q.getResultList();
	}

	@Override
	public Sintoma findById(Integer id) {

		return em.find(Sintoma.class, id);
	}

	@Override
	public void delete(Sintoma sintoma) {

		em.remove(em.contains(sintoma) ? sintoma : em.merge(sintoma));
		
	}
    
    

}
