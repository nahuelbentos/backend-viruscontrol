package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Ciudadano;

@Local
public interface CiudadanoDAOLocal {

	void persist(Ciudadano ciudadano);

	void merge(Ciudadano ciudadano);

	List<Ciudadano> findAll();

	Ciudadano findById(Integer id);

	void delete(Ciudadano ciudadano);

}
