package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Remote;

import uy.viruscontrol.model.entities.TipoEnfermedad;

//Creado con fines de prueba para Arquillian

@Remote
public interface TipoEnfermedadDAORemote {

	void persist(TipoEnfermedad tipoEnfermedad);

	void merge(TipoEnfermedad tipoEnfermedad);

	List<TipoEnfermedad> findAll();

	TipoEnfermedad findById(Integer id);

	void delete(TipoEnfermedad tipoEnfermedad);

}
