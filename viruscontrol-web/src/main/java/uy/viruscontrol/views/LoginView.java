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
	
	public LoginView(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
	
	public void login() {
		AuthResponse res = SessionBeanController.iniciarSesion(username, password);
		System.out.println(res);
		
	}
	

}
