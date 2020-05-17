package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.RecursoEnfermedad;

@Local
public interface RecursoEnfermedadDAOLocal {
	
	void persist(RecursoEnfermedad recursoEnfermedad);

	void merge(RecursoEnfermedad recursoEnfermedad);

	List<RecursoEnfermedad> findAll();

	RecursoEnfermedad findById(Recurso recurso, Enfermedad enfermedad);

	void delete(RecursoEnfermedad recursoEnfermedad);


}
