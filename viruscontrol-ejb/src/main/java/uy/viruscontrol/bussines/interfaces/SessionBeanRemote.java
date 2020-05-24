package uy.viruscontrol.bussines.interfaces;

import javax.ejb.Remote;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.model.entities.Usuario;

@Remote
public interface SessionBeanRemote {

	public AuthResponse iniciarSesion(String username, String password);
	
	public AuthResponse iniciarSesionConRedes(Usuario user, TipoUsuario tipoUsuario);
	
	public void validarDatosConRedes(Usuario user);

	public Usuario getUsuarioLogueado(String username);
	
	public void cerrarSesion(String username);
}
