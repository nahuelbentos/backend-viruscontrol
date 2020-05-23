package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.PrestadoraSalud;

@Local
public interface PrestadoraSaludDAOLocal {

	void persist(PrestadoraSalud prestadoraSalud);

	void merge(PrestadoraSalud prestadoraSalud);

	List<PrestadoraSalud> findAll();

	PrestadoraSalud findById(Integer id);

	void delete(PrestadoraSalud prestadoraSalud);
	
}
