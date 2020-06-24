package uy.viruscontrol.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.bussines.enumerated.TipoCaso;
import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.CiudadanoDAOLocal;
import uy.viruscontrol.model.entities.Caso;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.utils.DtExamenCiudadano;

/**
 * Session Bean implementation class CasoDAO
 */
@Stateless
@LocalBean
public class CasoDAO implements CasoDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
	@EJB CiudadanoDAOLocal daoCiudadanoLocal;
    /**
     * Default constructor. 
     */
    public CasoDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void persist(Caso caso) {

		em.persist(caso);
		
	}

	@Override
	public void merge(Caso caso) {
		
		em.merge(caso);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Caso> findAll() {
		
		Query q = em.createQuery("FROM Caso");
		return q.getResultList();
	}

	@Override
	public Caso findById(Integer id) {
		
		return em.find(Caso.class, id);
	}

	@Override
	public void delete(Caso caso) {

		em.remove(em.contains(caso) ? caso : em.merge(caso));
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Caso> findAllOrderByDepartamento() {
		
		return em.createQuery("SELECT c FROM Caso c WHERE tipoCaso <> :tipoCaso "
				+ "ORDER BY c.departamento,c.enfermedad,c.tipoCaso")
				.setParameter("tipoCaso", TipoCaso.EXPOSICION)
				.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DtExamenCiudadano> findAllExamenesByCiudadano(int idCiudadano){
		
		Ciudadano ciudadano = daoCiudadanoLocal.findById(idCiudadano);
		List<Caso> casosCiudadano = new ArrayList<Caso>();
		List<DtExamenCiudadano> examenesCiudadano = new ArrayList<DtExamenCiudadano>();
		
		
		if(ciudadano != null) {
			casosCiudadano = em.createQuery("SELECT c FROM Caso c WHERE ciudadano = :ciudadano")
								.setParameter("ciudadano", ciudadano)
								.getResultList();
		}
		for(Caso c : casosCiudadano) {
			DtExamenCiudadano dtExamCiudadano = new DtExamenCiudadano(c.getId(), c.getExamen().getNombre(),c.getTipoCaso());
			examenesCiudadano.add(dtExamCiudadano);
		}
		
		
		return examenesCiudadano;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Caso> findAllByCiudadano(int idCiudadano){
		Ciudadano ciudadano = daoCiudadanoLocal.findById(idCiudadano);
		if(ciudadano != null) {
			return em.createQuery("SELECT c FROM Caso c WHERE ciudadano = :ciudadano")
				.setParameter("ciudadano", ciudadano)
				.getResultList();
		} else
			return new ArrayList<Caso>();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Caso> findAllConfirmadosByCiudadano(int idCiudadano){
		Ciudadano ciudadano = daoCiudadanoLocal.findById(idCiudadano);
		if(ciudadano != null) {
			return em.createQuery("SELECT c FROM Caso c WHERE ciudadano = :ciudadano AND tipoCaso = :tipoCaso")
				.setParameter("ciudadano", ciudadano)
				.setParameter("tipoCaso", TipoCaso.CONFIRMADO)
				.getResultList();
		} else
			return new ArrayList<Caso>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Caso> findAllNotNotificated() {
		try {
			return em.createQuery("FROM Caso WHERE notificacionEnviada = false").getResultList();
		} catch (Exception e) {
			System.out.println("["+getClass().getCanonicalName()+"] ERROR: No se pudo obtener la lista de no notificados");
			return new ArrayList<Caso>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Caso> findAllConfirmados() {
		return em.createQuery("FROM Caso WHERE tipoCaso = :tipoCaso").setParameter("tipoCaso", TipoCaso.CONFIRMADO).getResultList();
	}
}
