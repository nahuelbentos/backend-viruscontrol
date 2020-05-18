package uy.viruscontrol.bussines.interfaces;

import javax.ejb.Remote;

import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.model.entities.Usuario;

@Remote
public interface UsuarioBeanRemote {
	
	public boolean autenticarUsuario(String username, String password);
	
	public Usuario getUsuario(String username);
	
	public boolean registrarPrimerIngreso(Usuario user, TipoUsuario tipoUser);
	
	public void actualizarDatos(Usuario user);
	
}
