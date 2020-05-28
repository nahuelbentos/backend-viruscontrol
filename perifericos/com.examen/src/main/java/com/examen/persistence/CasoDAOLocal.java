package com.examen.persistence;

import java.util.List;

import javax.ejb.Local;

import com.examen.entities.Caso;

@Local
public interface CasoDAOLocal {
	
	void persist(Caso caso);

	void merge(Caso caso);

	List<Caso> findAll();

	Caso findById(Integer id);

	void delete(Caso caso);
}
