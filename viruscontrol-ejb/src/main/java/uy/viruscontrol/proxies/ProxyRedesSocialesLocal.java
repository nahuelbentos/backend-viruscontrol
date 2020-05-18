package uy.viruscontrol.proxies;

import javax.ejb.Local;

@Local
public interface ProxyRedesSocialesLocal {

	public boolean authUsuario(String username);
	
}
