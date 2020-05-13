package uy.viruscontrol.dao;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.entities.Sintoma;


@Local
public interface SintomaDAOLocal {

	void persist(Sintoma sintoma);

	void merge(Sintoma sintoma);

	List<Sintoma> findAll();

	Sintoma findById(Integer id);

	void delete(Sintoma sintoma);

}
