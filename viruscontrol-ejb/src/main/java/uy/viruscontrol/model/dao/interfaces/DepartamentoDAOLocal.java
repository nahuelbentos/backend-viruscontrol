package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Departamento;

@Local
public interface DepartamentoDAOLocal {

	void persist(Departamento departamento);

	void merge(Departamento departamento);

	List<Departamento> findAll();

	Departamento findById(Integer id);

	void delete(Departamento departamento);



}
