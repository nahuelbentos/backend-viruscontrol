package com.recurso.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.recurso.model.DummyProveedor;
import com.recurso.model.entities.ProveedorRecurso;
import com.recurso.model.entities.TipoProveedor;

@Path("/proveedor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IProveedorRest {

	@GET
	@Path("/all")
	public List<DummyProveedor> getProveedores();
	
	@GET
	@Path("/tipo/all")
	public List<TipoProveedor> getTiposProveedor();
	
	@GET
	@Path("/tipo/{tipoProveedor}")
	public List<DummyProveedor> getProveedoresDeTipo(@PathParam("tipoProveedor") String codigoTipo);
	
	@GET
	@Path("/{codigoProveedor}")
	public List<ProveedorRecurso> getRecursosDisponibles(@PathParam("codigoProveedor") String codigoProveedor);
	
	@PUT
	@Path("/{codigoProveedor}/adquirir/{codigoRecurso}")
	public boolean adquirirRecursoDisponible(@PathParam("codigoProveedor") String codigoProveedor,
											 @PathParam("codigoRecurso") String codigoRecurso,
											 int cantidad);
	
	
	
}
