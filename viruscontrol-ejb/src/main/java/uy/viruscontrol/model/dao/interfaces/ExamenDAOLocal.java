package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Examen;

@Local
public interface ExamenDAOLocal {

	void persist(Examen examen);

	void merge(Examen examen);

	List<Examen> findAll();

	Examen findById(Integer id);

	void delete(Examen examen);
	
	public List<Examen> findAllByEnfermedad(int idEnfermedad);
}
