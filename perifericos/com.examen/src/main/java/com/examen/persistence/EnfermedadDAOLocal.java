package com.examen.persistence;

import java.util.List;

import javax.ejb.Local;

import com.examen.entities.Enfermedad;

@Local
public interface EnfermedadDAOLocal  {
	
	void persist(Enfermedad enfermedad);
	
	void merge(Enfermedad enfermedad);
	
	List<Enfermedad> findAll();
	
	Enfermedad findById(Integer id);
	
	void delete(Enfermedad enfermedad);

	boolean exist(String nombre);

}
