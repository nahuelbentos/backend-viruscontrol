package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.TipoEnfermedad;

@Local
public interface TipoEnfermedadDAOLocal {
	
	void persist(TipoEnfermedad tipoEnfermedad);
	
	void merge(TipoEnfermedad tipoEnfermedad);
	
	List<TipoEnfermedad> findAll();
	
	TipoEnfermedad findById(Integer id);
	
	void delete(TipoEnfermedad tipoEnfermedad);

	boolean exist(String nombre);
}
