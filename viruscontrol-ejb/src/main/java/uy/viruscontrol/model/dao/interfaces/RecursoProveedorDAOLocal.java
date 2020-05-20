package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.IdRecursoProveedor;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.RecursoProveedor;

@Local
public interface RecursoProveedorDAOLocal {
	
	void persist(RecursoProveedor recProv);

	void merge(RecursoProveedor recProv);

	List<RecursoProveedor> findAll();

	RecursoProveedor findById(IdRecursoProveedor id);

	void delete(IdRecursoProveedor id);
	
	public List<RecursoProveedor> findAllByRecurso(Recurso recurso);
}
