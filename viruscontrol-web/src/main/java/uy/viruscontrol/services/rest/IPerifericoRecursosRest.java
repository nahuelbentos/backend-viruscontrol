package uy.viruscontrol.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/proveedor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IPerifericoRecursosRest {
	
	@POST
	@Path("{codigoProveedor}/recursos/{codigoRecurso}")
	public Response addRecurso(@PathParam("codigoProveedor") String codigoPrvPeriferico,
							@PathParam("codigoRecurso") String codigoRecPeriferico);
	
	@DELETE
	@Path("{codigoProveedor}/recursos/{codigoRecurso}")
	public Response deleteRecurso(@PathParam("codigoProveedor") String codigoPrvPeriferico, 
								@PathParam("codigoRecurso") String codigoRecPeriferico);
	
}