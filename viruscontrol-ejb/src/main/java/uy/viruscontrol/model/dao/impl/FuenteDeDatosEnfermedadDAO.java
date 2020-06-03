package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uy.viruscontrol.model.dao.interfaces.FuenteDeDatosEnfermedadDAOLocal;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.FuenteDeDatosEnfermedad;


@Stateless
@Local(FuenteDeDatosEnfermedadDAOLocal.class)
public class FuenteDeDatosEnfermedadDAO implements FuenteDeDatosEnfermedadDAOLocal {
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;

	public FuenteDeDatosEnfermedadDAO() {
		
	}
	
	@Override
	public void persist(FuenteDeDatosEnfermedad fuenteEnfermedad) {
		em.persist(fuenteEnfermedad);
	}
	
	@Override
	public void merge(FuenteDeDatosEnfermedad fuenteEnfermedad) {
		em.merge(fuenteEnfermedad);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FuenteDeDatosEnfermedad> findAll() {
		return em.createQuery("FROM FuenteDeDatosEnfermedad").getResultList();
	}
	
	@Override
	public FuenteDeDatosEnfermedad findById(int id) {
		return em.find(FuenteDeDatosEnfermedad.class, id);
	}

	@Override
	public void delete(FuenteDeDatosEnfermedad fuenteEnfermedad) {
		em.remove(em.contains(fuenteEnfermedad) ? fuenteEnfermedad : em.merge(fuenteEnfermedad));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FuenteDeDatosEnfermedad> findAllByEnfermedad(Enfermedad enfermedad) {
		return em.createQuery("FROM FuenteDeDatosEnfermedad WHERE enfermedad = :enfermedad")
				.setParameter("enfermedad", enfermedad)
				.getResultList();
	}
	
}
