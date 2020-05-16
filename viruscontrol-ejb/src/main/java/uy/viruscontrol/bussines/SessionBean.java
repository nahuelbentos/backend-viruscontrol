package uy.viruscontrol.bussines;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.bussines.interfaces.SessionBeanLocal;
import uy.viruscontrol.bussines.interfaces.SessionBeanRemote;
import uy.viruscontrol.model.entities.Usuario;

@Singleton
@LocalBean
@Startup
public class SessionBean implements SessionBeanRemote, SessionBeanLocal {

	private List<Usuario> userConectados = new ArrayList<Usuario>();
	
	public SessionBean() {}

	@Override
	public AuthResponse iniciarSesion(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
