package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Gerente;

@Local
public interface GerenteDAOLocal {

	List<Gerente> findAll();

	void delete(Gerente gerente);

	Gerente findById(Integer id);

	void merge(Gerente gerente);

	void persist(Gerente gerente);

	
}
