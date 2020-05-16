package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.PlantillaNotificacion;


@Local
public interface PlantillaNotificacionDAOLocal {
	
	void persist(PlantillaNotificacion plantillaNotificacion);

	void merge(PlantillaNotificacion plantillaNotificacion);

	List<PlantillaNotificacion> findAll();
	
	PlantillaNotificacion findById(Integer id);
	
	void delete(PlantillaNotificacion plantillaNotificacion);

}
