package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Notificacion;

@Local
public interface NotificacionDAOLocal {

	void persist(Notificacion notificacion);

	void merge(Notificacion notificacion);

	List<Notificacion> findAll();
	
	Notificacion findById(Integer id);
	
	void delete(Notificacion notificacion);
}
