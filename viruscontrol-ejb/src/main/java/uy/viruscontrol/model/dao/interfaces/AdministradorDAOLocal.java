package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Administrador;

@Local
public interface AdministradorDAOLocal {

	void persist(Administrador administrador);

	void merge(Administrador administrador);

	List<Administrador> findAll();

	void delete(Administrador administrador);

	Administrador findById(Integer id);

	
	
}
