package uy.viruscontrol.services.rest;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.bussines.interfaces.SessionBeanLocal;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Medico;

@ApplicationScoped
public class AuthRest implements IAuthRest {

	@EJB
	private SessionBeanLocal sessionEjb;

	@Override
	public Response ping() {
		return Response.status(Status.OK).build();
	}

	@Override
	public AuthResponse iniciarSesionMedico(Medico user) {
		return sessionEjb.iniciarSesionConRedes(user, TipoUsuario.MEDICO);
	}

	@Override
	public AuthResponse iniciarSesionCiudadano(Ciudadano user) {
		return sessionEjb.iniciarSesionConRedes(user, TipoUsuario.CIUDADANO);
	}
	

}
