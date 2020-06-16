package uy.viruscontrol.services.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.viruscontrol.bussines.enumerated.TipoCaso;
import uy.viruscontrol.bussines.map.MapaInteractivo;
import uy.viruscontrol.model.entities.TipoRecurso;

@Path("/home")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IServicesPublicHome {

	
	@GET
	@Path("/mapa-interactivo")
	public MapaInteractivo getMapa();
	
	@GET
	@Path("/estados-enfermedad")
	public List<TipoCaso> getEstadoEnfermedad();
	
	@GET
	@Path("/tipo-recursos")
	public List<TipoRecurso> getTiposRecursos();
	
	@GET
	@Path("/barrios")
	public List<String> getBarrios();
	
}
