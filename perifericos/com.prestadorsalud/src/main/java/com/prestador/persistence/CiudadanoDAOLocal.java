package com.prestador.persistence;

import java.util.List;

import javax.ejb.Local;

import com.prestador.entities.Ciudadano;


@Local
public interface CiudadanoDAOLocal {

	void persist(Ciudadano ciudadano);

	void merge(Ciudadano ciudadano);

	List<Ciudadano> findAll();

	Ciudadano findById(Integer id);

	void delete(Ciudadano ciudadano);

}
