package com.recurso.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.recurso.model.entities.DummyProveedor;
import com.recurso.model.entities.DummyRecursoDisponible;
import com.recurso.model.entities.Recurso;
import com.recurso.model.entities.TipoProveedor;
import com.recurso.model.entities.TipoRecurso;

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
	public List<DummyRecursoDisponible> getRecursosDisponibles(@PathParam("codigoProveedor") String codigoProveedor);
	
	@PUT
	@Path("/{codigoProveedor}/{codigoRecurso}/vender")
	public boolean adquirirRecursoDisponible(@PathParam("codigoProveedor") String codigoProveedor,
											 @PathParam("codigoRecurso") String codigoRecurso,
											 int cantidad);
	
	@PUT
	@Path("/{codigoProveedor}/{codigoRecurso}/comprar")
	public int aumentarStockRecurso(@PathParam("codigoProveedor") String codigoProveedor,
										@PathParam("codigoRecurso") String codigoRecurso,
										int cantidad);
	
	@GET
	@Path("/{codigoProveedor}/{codigoRecurso}/stock")
	public int consultarStockRecurso(@PathParam("codigoProveedor") String codigoProveedor,
										@PathParam("codigoRecurso") String codigoRecurso);
	
	
	@GET
	@Path("/all/barrios")
	public List<String> getAllBarrios();
	
	
	@GET
	@Path("/ciudad/{ciudad}/barrio/{barrio}")
	List<DummyProveedor> getRecursosDisponiblesPorCiudadBarrio(@PathParam("ciudad") String ciudad, 
																@PathParam("barrio") String barrio);
	
	@GET
	@Path("/all/recursos")
	List<Recurso> getAllRecursos();
	
	@GET
	@Path("/all/tiposrecursos")
	List<TipoRecurso> getAllTipoDeRecursos();
	

	
}
