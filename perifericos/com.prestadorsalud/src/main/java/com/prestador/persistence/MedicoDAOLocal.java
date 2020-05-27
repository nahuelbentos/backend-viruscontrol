package com.prestador.persistence;

import java.util.List;

import javax.ejb.Local;

import com.prestador.entities.Medico;
import com.prestador.entities.PrestadoraSalud;

@Local
public interface MedicoDAOLocal {

	void persist(Medico medico);

	void merge(Medico medico);

	List<Medico> findAll();

	Medico findById(Integer id);

	void delete(Medico medico);

	List<Medico> findAllByPrestadoraSalud(PrestadoraSalud p);
	
}
