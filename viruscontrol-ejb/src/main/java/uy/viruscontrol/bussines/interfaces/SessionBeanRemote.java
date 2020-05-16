package uy.viruscontrol.bussines.interfaces;

import javax.ejb.Remote;

import uy.viruscontrol.bussines.enumerated.AuthResponse;

@Remote
public interface SessionBeanRemote {

	public AuthResponse iniciarSesion(String username, String password);
	
	
}
