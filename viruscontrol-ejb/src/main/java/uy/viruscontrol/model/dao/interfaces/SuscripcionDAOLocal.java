package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;


import uy.viruscontrol.model.entities.Suscripcion;

@Local
public interface SuscripcionDAOLocal {
	void persist(Suscripcion suscripcion);

	void merge(Suscripcion suscripcion);

	List<Suscripcion> findAll();

	Suscripcion findById(Integer id);

	void delete(Suscripcion suscripcion);



}
