package uy.viruscontrol.bussines.serviceagents;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.ProveedorRecursos;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.utils.DtRecursosProveedor;

@Local
public interface ServiceAgentProveedorRecursoLocal {
	
	public List<ProveedorRecursos> getProveedoresPeriferico();
	
	public List<Recurso> getRecursosProvPeriferico(String codigoPeriferico);
	
	public boolean comprarRecursoDisponible(String codigoProveedor, String codigoRecurso);

	public List<DtRecursosProveedor> getRecursosDisponiblesPorCiudadBarrio(String ciudad, String barrio);

	public ProveedorRecursos findProveedor(String codigo);

}
