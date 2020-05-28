package com.prestador.persistence;

import java.util.List;

import javax.ejb.Local;

import com.prestador.entities.PrestadoraSalud;

@Local
public interface PrestadoraSaludDAOLocal {

	void persist(PrestadoraSalud prestadoraSalud);

	void merge(PrestadoraSalud prestadoraSalud);

	List<PrestadoraSalud> findAll();

	PrestadoraSalud findById(Integer id);

	void delete(PrestadoraSalud prestadoraSalud);
	
}
