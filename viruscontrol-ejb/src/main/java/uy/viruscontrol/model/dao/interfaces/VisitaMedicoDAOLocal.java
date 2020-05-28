package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.VisitaMedico;

@Local
public interface VisitaMedicoDAOLocal {

	void persist(VisitaMedico visitaMedico) throws PersistenceException;

	void merge(VisitaMedico visitaMedico);

	List<VisitaMedico> findAll();

	VisitaMedico findById(Integer id);

	void delete(VisitaMedico visitaMedico);

	List<VisitaMedico> findByMedico(Medico m);
	
}
