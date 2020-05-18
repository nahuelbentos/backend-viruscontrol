package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.entities.PrestadoraSalud;
import uy.viruscontrol.model.dao.interfaces.MedicoDAOLocal;
import uy.viruscontrol.model.entities.Medico;


@Stateless
@LocalBean
public class MedicoDAO implements MedicoDAOLocal{
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
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
