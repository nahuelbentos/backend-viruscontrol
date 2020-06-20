package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Caso;
import uy.viruscontrol.utils.DtExamenCiudadano;

@Local
public interface CasoDAOLocal {
	
	void persist(Caso caso);

	void merge(Caso caso);

	List<Caso> findAll();

	Caso findById(Integer id);

	void delete(Caso caso);
	
	List<Caso> findAllOrderByDepartamento();

	List<DtExamenCiudadano> findAllExamenesByCiudadano(int idCiudadano);

	List<Caso> findAllNotNotificated();

	
	
	
}
