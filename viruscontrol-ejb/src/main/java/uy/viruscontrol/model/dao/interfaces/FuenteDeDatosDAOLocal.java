package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.FuenteDeDatos;

@Local
public interface FuenteDeDatosDAOLocal {

	void persist(FuenteDeDatos fuente);

	void merge(FuenteDeDatos fuente);

	List<FuenteDeDatos> findAll();


	void delete(FuenteDeDatos fuente);

	FuenteDeDatos findById(int id);

}
