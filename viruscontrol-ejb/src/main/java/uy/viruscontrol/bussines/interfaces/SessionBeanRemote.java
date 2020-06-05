package uy.viruscontrol.bussines.interfaces;

import javax.ejb.Remote;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.model.entities.Usuario;
import uy.viruscontrol.security.UserAuthFE;

@Remote
public interface SessionBeanRemote {

	public AuthResponse iniciarSesion(String username, String password);
	
	public UserAuthFE iniciarSesionConRedes(Usuario user, TipoUsuario tipoUsuario);
	
	public void validarDatosConRedes(Usuario user);

	public Usuario getUsuarioLogueado(String token);
	
	public String getTokenByUsername(String username);
	
	public void cerrarSesion(String token);

//	public DtSessionToken getTokenUsuarioLogueado(String username);

//	public boolean validateAuthentication(DtSessionToken token);

	public boolean validateAuthentication(String token);
}
