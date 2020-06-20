package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.bussines.enumerated.TipoNotificacion;
import uy.viruscontrol.model.entities.ConfiguracionNotificaciones;

@Local
public interface ConfiguracionNotificacionesDAOLocal {
	void persist(ConfiguracionNotificaciones configuracion);
	void merge(ConfiguracionNotificaciones configuracion);
	List<ConfiguracionNotificaciones> findAll();
	ConfiguracionNotificaciones findById(TipoNotificacion tipoNotificacion);
	void delete(ConfiguracionNotificaciones configuracion);
}
