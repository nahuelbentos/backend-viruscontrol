package uy.viruscontrol.bussines.serviceagents;

import java.util.List;

import javax.ejb.Remote;

import uy.viruscontrol.model.entities.ProveedorRecursos;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.TipoRecurso;
import uy.viruscontrol.utils.DtRecursosProveedor;

@Remote
public interface ServiceAgentProveedorRecursoRemote {
	
	List<ProveedorRecursos> getProveedoresPeriferico();

	public List<DtRecursosProveedor> getRecursosDisponiblesPorCiudadBarrio(String ciudad, String barrio);

	public ProveedorRecursos findProveedor(String codigo);
	
	public List<TipoRecurso> getAllTipoDeRecursosPeriferico();

	public List<Recurso> getAllRecursosPeriferico();
}
