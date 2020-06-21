package uy.viruscontrol.model.dao.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.bussines.enumerated.TipoNotificacion;
import uy.viruscontrol.model.dao.interfaces.ConfiguracionNotificacionesDAOLocal;
import uy.viruscontrol.model.entities.ConfiguracionNotificaciones;

@Stateless
@Local(ConfiguracionNotificacionesDAOLocal.class)
public class ConfiguracionNotificacionesDAO implements ConfiguracionNotificacionesDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
	public ConfiguracionNotificacionesDAO() {
        super();
    }

	@Override
	public void persist(ConfiguracionNotificaciones configuracion) {
		em.persist(configuracion);
	}

	@Override
	public void merge(ConfiguracionNotificaciones configuracion) {
		em.merge(configuracion);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfiguracionNotificaciones> findAll() {
	//	return em.createQuery("FROM Caso").getResultList();
		Query q = em.createQuery("SELECT m FROM ConfiguracionNotificaciones m");
		return q.getResultList();
		
	}

	@Override
	public ConfiguracionNotificaciones findById(TipoNotificacion tipo) {
		return em.find(ConfiguracionNotificaciones.class, tipo);
	}

	@Override
	public void delete(ConfiguracionNotificaciones configuracion) {
		em.remove(em.contains(configuracion) ? configuracion : em.merge(configuracion));
	}
}
