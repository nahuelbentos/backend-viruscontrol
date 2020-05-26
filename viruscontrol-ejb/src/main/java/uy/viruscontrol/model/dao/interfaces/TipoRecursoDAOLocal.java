package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.TipoRecurso;

@Local
public interface TipoRecursoDAOLocal {

	void persist(TipoRecurso tipoRecurso);

	void merge(TipoRecurso tipoRecurso);

	List<TipoRecurso> findAll();

	TipoRecurso findById(Integer id);

	void delete(TipoRecurso tipoRecurso);

	boolean exist(String nombre);

}
