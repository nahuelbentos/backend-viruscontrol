package uy.viruscontrol.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.security.UserAuthFE;

@Path("/autenticacion")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IAuthRest{
	
	@POST
	@Path("/entrar/medico")
	public UserAuthFE iniciarSesionMedico(Medico user);
	
	@POST
	@Path("/entrar/ciudadano")
	public UserAuthFE iniciarSesionCiudadano(Ciudadano user);
	
	@PUT
	@Path("/validar_datos")
	public Response validarDatosConRedes(Ciudadano user);
	
	@DELETE
	@Path("/salir")
	public Response cerrarSesion(@HeaderParam("authorization")String token);
	
	@GET
	@Path("/ping")
	public Response ping();
	
}
