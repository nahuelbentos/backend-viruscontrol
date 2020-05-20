package uy.viruscontrol.views;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.controllers.SessionBeanController;

@Named("LoginView")
@RequestScoped
public class LoginView {
	
	private String username;
	private String password;
	private String mensaje;

	public LoginView(String username, String password, String mensaje) {
		super();
		this.username = username;
		this.password = password;
		this.mensaje = mensaje;
	}
	public LoginView() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String login() {
		AuthResponse res = SessionBeanController.iniciarSesion(username, password);
		if (res.equals(AuthResponse.OK)) {
			SessionBeanController.getUsuarioLogeado(username);
			return "exito";
		} else {
			this.mensaje = "Usuario inexistente o las credenciales son incorrectas.";
			return "fallo";
			
		}
	}
	

}
