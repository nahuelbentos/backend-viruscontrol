package com.examen.persistence;

import java.util.List;

import javax.ejb.Local;

import com.examen.entities.ProveedorExamen;


@Local
public interface ProveedorExamenDAOLocal {

	void persist(ProveedorExamen proveedorExamen);

	void merge(ProveedorExamen proveedorExamen);

	List<ProveedorExamen> findAll();

	ProveedorExamen findById(Integer id);

	void delete(ProveedorExamen proveedorExamen);

	public List<ProveedorExamen> findAllByEnfermedadId(int idEnfermedad);

}
