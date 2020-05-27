package com.prestador.persistence;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.prestador.entities.Medico;
import com.prestador.entities.PrestadoraSalud;


@Stateless
@Local(MedicoDAOLocal.class)
public class MedicoDAO implements MedicoDAOLocal{
	@PersistenceContext(unitName = "com.prestadorsaludPersistenceUnit")
    protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public MedicoDAO() {
        // TODO Auto-generated constructor stub
    }
   
	@Override
	public void persist(Medico medico) {
	
		em.persist(medico);
		
	}

	@Override
	public void merge(Medico medico) {
		
		em.merge(medico);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medico> findAll() {

		Query q = em.createQuery("SELECT m FROM Medico m");
		return q.getResultList();
	}

	@Override
	public Medico findById(Integer id) {
		
		return em.find(Medico.class, id);
	}

	@Override
	public void delete(Medico medico) {
		
		em.remove(em.contains(medico) ? medico : em.merge(medico));
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Medico> findAllByPrestadoraSalud(PrestadoraSalud p) {
		return em.createQuery("From Medico WHERE prestadoraSalud = :prestadoraSalud")
				.setParameter("prestadoraSalud", p)
				.getResultList();
	}
}
