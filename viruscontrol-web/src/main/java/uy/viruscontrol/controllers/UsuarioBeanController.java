package uy.viruscontrol.controllers;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.viruscontrol.bussines.interfaces.UsuarioBeanRemote;
import uy.viruscontrol.model.entities.Usuario;

public class UsuarioBeanController {

	private static UsuarioBeanRemote iUsuarioBean = lookupRemoteUsuarioBean();
	
	public static boolean registrarUsuario(Usuario user) {
		return iUsuarioBean.registrarUsuario(user);
	}
	
	private static UsuarioBeanRemote lookupRemoteUsuarioBean(){
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");  
		props.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		
		Context ctx;
		try {
			ctx = new InitialContext(props);
			
			String jndiName = "ejb:viruscontrol/viruscontrol-ejb/UsuarioBean!uy.viruscontrol.bussines.interfaces.UsuarioBeanRemote";
			return (UsuarioBeanRemote)ctx.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}