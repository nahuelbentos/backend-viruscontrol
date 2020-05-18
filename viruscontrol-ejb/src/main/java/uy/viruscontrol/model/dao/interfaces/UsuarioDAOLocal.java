package uy.viruscontrol.model.dao.interfaces;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Usuario;

@Local
public interface UsuarioDAOLocal {

	public Usuario findByUsername(String username);
	
	public boolean existUserByUsername(String username);
	
}
