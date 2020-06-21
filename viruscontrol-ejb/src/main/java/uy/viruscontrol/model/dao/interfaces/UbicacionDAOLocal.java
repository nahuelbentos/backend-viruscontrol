package uy.viruscontrol.model.dao.interfaces;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Ubicacion;

@Local
public interface UbicacionDAOLocal {
	void persist(Ubicacion ubicacion);
	void merge(Ubicacion ubicacion);
	List<Ubicacion> findAll();
	Ubicacion findById(Integer id);
	void delete(Ubicacion ubicacion);
	List<Ubicacion> findAllDateFiltered(Calendar fechaDesde);
}
