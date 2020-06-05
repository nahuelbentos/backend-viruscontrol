package uy.viruscontrol.services.rest;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.bussines.interfaces.SessionBeanLocal;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.security.UserAuthFE;

@ApplicationScoped
public class AuthRest implements IAuthRest {

	@EJB
	private SessionBeanLocal sessionEjb;

	@Override
	public Response ping() {
		return Response.status(Status.OK).build();
	}

	@Override
	public UserAuthFE iniciarSesionMedico(Medico user) {
		return sessionEjb.iniciarSesionConRedes(user, TipoUsuario.MEDICO);
	}

	@Override
	public UserAuthFE iniciarSesionCiudadano(Ciudadano user) {
		return sessionEjb.iniciarSesionConRedes(user, TipoUsuario.CIUDADANO);
	}

	@Override
	public Response validarDatosConRedes(Ciudadano user) {
		try {
			sessionEjb.validarDatosConRedes(user);
			
			return Response.status(Status.OK).build();
		} catch (Exception e) { 
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public Response cerrarSesion(String token) {
		try {
			sessionEjb.cerrarSesion(token);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}	

}
