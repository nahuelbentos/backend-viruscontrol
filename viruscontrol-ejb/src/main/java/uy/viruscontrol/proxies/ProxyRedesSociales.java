package uy.viruscontrol.proxies;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class ProxyRedesSociales implements ProxyRedesSocialesLocal {

    public ProxyRedesSociales() { }

	@Override
	public boolean authUsuario(String username) {
		// TODO Auto-generated method stub
		return true;
	}
    
    
    
}
