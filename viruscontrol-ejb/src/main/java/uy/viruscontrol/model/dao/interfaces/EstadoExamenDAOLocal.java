package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.EstadoExamen;


@Local
public interface EstadoExamenDAOLocal {
	
	void persist(EstadoExamen estadoExamen);

	void merge(EstadoExamen estadoExamen);

	List<EstadoExamen> findAll();

	EstadoExamen findById(Integer id);

	void delete(EstadoExamen estadoExamen);
}
