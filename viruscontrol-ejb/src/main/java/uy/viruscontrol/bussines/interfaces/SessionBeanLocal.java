package uy.viruscontrol.bussines.interfaces;

import javax.ejb.Local;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.model.entities.Usuario;

@Local
public interface SessionBeanLocal {

	public AuthResponse iniciarSesion(String username, String password);
	
	public AuthResponse iniciarSesionConRedes(Usuario user, TipoUsuario tipoUsuario);
	
	public void validarDatosConRedes(Usuario user);
	
}
