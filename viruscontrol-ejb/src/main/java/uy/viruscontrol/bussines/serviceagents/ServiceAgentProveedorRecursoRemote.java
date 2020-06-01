package uy.viruscontrol.bussines.serviceagents;

import java.util.List;

import javax.ejb.Remote;

import uy.viruscontrol.model.entities.ProveedorRecursos;

@Remote
public interface ServiceAgentProveedorRecursoRemote {
	
	List<ProveedorRecursos> getProveedoresPeriferico();
}
