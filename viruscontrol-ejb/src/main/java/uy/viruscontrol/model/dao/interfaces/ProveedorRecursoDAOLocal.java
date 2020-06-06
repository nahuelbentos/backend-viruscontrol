package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.ProveedorRecursos;

@Local
public interface ProveedorRecursoDAOLocal {

	void persist(ProveedorRecursos proveedorRecursos);

	void merge(ProveedorRecursos proveedorRecursos);

	List<ProveedorRecursos> findAll();

	ProveedorRecursos findById(Integer id);

	void delete(ProveedorRecursos proveedorRecursos);

	public ProveedorRecursos findByExternalId(String codigo);

}
