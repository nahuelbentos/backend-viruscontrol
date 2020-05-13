package uy.viruscontrol.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.entities.Sintoma;

/**
 * Session Bean implementation class Sintoma
 */
@Stateless
@LocalBean
public class SintomaDAO implements SintomaDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;

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
