package uy.viruscontrol.model.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.VisitaMedicoDAOLocal;
import uy.viruscontrol.model.entities.VisitaMedico;

@Stateless
@LocalBean
public class VisitaMedicoDAO implements VisitaMedicoDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
	public VisitaMedicoDAO() {
		
	}
	
	@Override
	public void persist(VisitaMedico visitaMedico) {
	
//		em.persist(visitaMedico);
		// [WORKAROUND] se utiliza native query porque revienta mapeando los tipos
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		em.createNativeQuery(
				"INSERT INTO visita_medico (ciudadano, fechaasignacion, medico) " + 
				"VALUES (" + visitaMedico.getCiudadano().getIdUsuario() + ", " +
				"'"+sdf.format(visitaMedico.getFechaAsignacion().getTime()) + "', " + 
				visitaMedico.getMedico().getIdUsuario() + ");").executeUpdate();
		
	}
	
	@Override
	public void merge(VisitaMedico visitaMedico) {
		
		em.merge(visitaMedico);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VisitaMedico> findAll() {

		Query q = em.createQuery("SELECT v FROM VisitaMedico v");
		return q.getResultList();
	}

	
	@Override
	public VisitaMedico findById(Integer id) {
		
		return em.find(VisitaMedico.class, id);
	}
	
	@Override
	public void delete(VisitaMedico visitaMedico) {
		
		em.remove(em.contains(visitaMedico) ? visitaMedico : em.merge(visitaMedico));
		
	}
	
	
	
	
}
