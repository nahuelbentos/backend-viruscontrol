package uy.viruscontrol.utils;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.model.entities.Usuario;

public class UserAuthFE {

	private AuthResponse response;
	private Usuario usuario;
	public AuthResponse getResponse() {
		return response;
	}
	public void setResponse(AuthResponse response) {
		this.response = response;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public UserAuthFE() {
		super();
	}
	
	
	
}
