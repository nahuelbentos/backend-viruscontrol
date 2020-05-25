package uy.viruscontrol.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uy.viruscontrol.bussines.enumerated.AuthResponse;

import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Medico;

@Path("/autenticacion")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IAuthRest{
	
	@POST
	@Path("/entrar/medico")
	public AuthResponse iniciarSesionMedico(Medico user);
	
	@POST
	@Path("/entrar/ciudadano")
	public AuthResponse iniciarSesionCiudadano(Ciudadano user);
	
	@PUT
	@Path("/validar_datos")
	public Response validarDatosConRedes(Ciudadano user);
	
	@DELETE
	@Path("/salir/{username}")
	public Response cerrarSesion(@PathParam("username")String username);
	
	@GET
	@Path("/ping")
	public Response ping();
}
