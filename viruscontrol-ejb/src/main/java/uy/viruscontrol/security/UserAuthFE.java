package uy.viruscontrol.security;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.model.entities.Usuario;

public class UserAuthFE {

	private AuthResponse response;
	private Usuario usuario;
	private String sessionToken;

	public UserAuthFE() {
		super();
	}
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
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	@Override
	public String toString() {
		return "UserAuthFE [response=" + response + ", usuario=" + usuario + ", sessionToken=" + sessionToken + "]";
	}
	
	
	
}
