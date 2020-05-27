package com.prestador.persistence;

import javax.ejb.Local;

import com.prestador.entities.Usuario;

@Local
public interface UsuarioDAOLocal {

	public Usuario findByUsername(String username);
	
	public boolean existUserByUsername(String username);
	
}
