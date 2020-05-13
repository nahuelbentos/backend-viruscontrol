package uy.viruscontrol.dao;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.entities.Recurso;

@Local
public interface RecursoDAOLocal {

	void persist(Recurso recurso);

	void merge(Recurso recurso);

	List<Recurso> findAll();

	Recurso findById(Integer id);

	void delete(Recurso recurso);
}
