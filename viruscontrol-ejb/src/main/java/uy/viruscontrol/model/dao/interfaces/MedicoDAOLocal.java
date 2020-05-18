package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.PrestadoraSalud;
import uy.viruscontrol.model.entities.Medico;

@Local
public interface MedicoDAOLocal {

	void persist(Medico medico);

	void merge(Medico medico);

	List<Medico> findAll();

	Medico findById(Integer id);

	void delete(Medico medico);

	List<Medico> findAllByPrestadoraSalud(PrestadoraSalud p);
	
}
