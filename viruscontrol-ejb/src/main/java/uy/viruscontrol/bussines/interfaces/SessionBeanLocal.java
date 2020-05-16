package uy.viruscontrol.bussines.interfaces;

import javax.ejb.Local;

import uy.viruscontrol.bussines.enumerated.AuthResponse;

@Local
public interface SessionBeanLocal {

	public AuthResponse iniciarSesion(String username, String password);
	
}
