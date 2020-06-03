package uy.viruscontrol.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.viruscontrol.bussines.map.MapaInteractivo;

@Path("/home")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IServicesPublicHome {

	
	@GET
	@Path("/mapa-interactivo")
	public MapaInteractivo getMapa();
	
}
