package uy.viruscontrol.bussines.interfaces;

import javax.ejb.Local;

@Local
public interface UsuarioBeanLocal {

	public boolean iniciarSesion(String username, String password);
	
}
