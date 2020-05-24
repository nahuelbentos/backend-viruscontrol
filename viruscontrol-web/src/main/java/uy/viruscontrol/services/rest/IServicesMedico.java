package uy.viruscontrol.services.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uy.viruscontrol.utils.VisitaPendiente;

@Path("/medico")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IServicesMedico {
	
	@GET
	@Path("/{username}/visita_pendiente/all")
	public List<VisitaPendiente> getVisitasPendientes(@PathParam("username")String username);
	
	
	@GET
	@Path("/ping")
	public Response ping();
	

}
