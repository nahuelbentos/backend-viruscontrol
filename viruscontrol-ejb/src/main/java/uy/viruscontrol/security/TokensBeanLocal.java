package uy.viruscontrol.security;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Usuario;

@Local
public interface TokensBeanLocal {
//	public DtSessionToken getNewToken(Usuario usuario);
	public String getToken(Usuario usuario);
}
