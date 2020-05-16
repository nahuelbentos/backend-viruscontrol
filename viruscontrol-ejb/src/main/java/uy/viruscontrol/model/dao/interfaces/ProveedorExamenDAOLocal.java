package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.ProveedorExamen;


@Local
public interface ProveedorExamenDAOLocal {

	void persist(ProveedorExamen proveedorExamen);

	void merge(ProveedorExamen proveedorExamen);

	List<ProveedorExamen> findAll();

	ProveedorExamen findById(Integer id);

	void delete(ProveedorExamen proveedorExamen);

	

	

}
