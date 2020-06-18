package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.TipoRecurso;

@Local
public interface RecursoDAOLocal {

	void persist(Recurso recurso);

	void merge(Recurso recurso);

	List<Recurso> findAll();

	Recurso findById(Integer id);

	void delete(Recurso recurso);

	boolean exist(String nombre);

	List<Recurso> findRecursoByTipoRecurso(TipoRecurso tipoRecurso);

	List<Recurso> getAllRecursos();

	List<Recurso> findAllByEnfermedad(int idEnfermedad);

	Recurso findByExternalId(String codigo);
}
