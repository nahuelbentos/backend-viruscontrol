package uy.viruscontrol.controllers;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.bussines.interfaces.SessionBeanRemote;
import uy.viruscontrol.model.entities.Usuario;

public class SessionBeanController {

	private static SessionBeanRemote iSessionBean = lookupRemoteSessionBean();;
	
	public static AuthResponse iniciarSesion(String username, String password) {
		return iSessionBean.iniciarSesion(username, password);
	}
	
	public static Usuario getUsuarioLogeado(String username) {
		return iSessionBean.getUsuarioLogueado(username);
	}
	
	private static SessionBeanRemote lookupRemoteSessionBean(){
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");  
		props.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		
		Context ctx;
		try {
			ctx = new InitialContext(props);
			
			String jndiName = "ejb:viruscontrol/viruscontrol-ejb/SessionBean!uy.viruscontrol.bussines.interfaces.SessionBeanRemote";
			return (SessionBeanRemote)ctx.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
