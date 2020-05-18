package uy.viruscontrol.services.rest;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.bussines.interfaces.SessionBeanLocal;

import uy.viruscontrol.model.entities.Usuario;

@ApplicationScoped
public class AuthRest implements IAuthRest {

	@EJB
	private SessionBeanLocal sessionEjb;
	
	@Override
	public AuthResponse iniciarSesion(Usuario user,TipoUsuario tipoUsuario) {
		return sessionEjb.iniciarSesionConRedes(user, tipoUsuario);
	}

}
