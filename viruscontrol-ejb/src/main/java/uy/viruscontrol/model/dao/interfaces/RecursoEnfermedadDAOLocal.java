package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.IdRecursoEnfermedad;
import uy.viruscontrol.model.entities.RecursoEnfermedad;

@Local
public interface RecursoEnfermedadDAOLocal {
	
	void persist(RecursoEnfermedad recursoEnfermedad);

	void merge(RecursoEnfermedad recursoEnfermedad);

	List<RecursoEnfermedad> findAll();

	RecursoEnfermedad findById(Integer id);

	void delete(RecursoEnfermedad recursoEnfermedad);

	//boolean exist(RecursoEnfermedad recursoEnfermedad);

}
