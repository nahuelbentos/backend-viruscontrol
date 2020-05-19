package uy.viruscontrol.bussines.interfaces;

import javax.ejb.Remote;

import uy.viruscontrol.model.entities.Usuario;

@Remote
public interface UsuarioBeanRemote {
	
	public boolean registrarUsuario(Usuario user);
	
}
