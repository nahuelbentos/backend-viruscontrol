package uy.viruscontrol.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.model.entities.Usuario;

@Path("/autenticacion")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IAuthRest{
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/entrar/{tipoUsuario}")
	public AuthResponse iniciarSesion(Usuario user, @PathParam("tipoUsuario") TipoUsuario tipoUsuario );
	
}
