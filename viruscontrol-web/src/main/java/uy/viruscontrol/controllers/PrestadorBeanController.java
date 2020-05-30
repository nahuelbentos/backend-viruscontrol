package uy.viruscontrol.controllers;


import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.viruscontrol.bussines.interfaces.PrestadorBeanRemote;
import uy.viruscontrol.model.entities.PrestadoraSalud;

public class PrestadorBeanController {
	
	
	
	private static PrestadorBeanRemote prestadorBeanRemote = lookupRemotePrestadorBean();
	
	public static boolean crearPrestadorSalud(String nombrePrestador) {
		return prestadorBeanRemote.nuevoPrestador(nombrePrestador);
	}
	
	
	public static List<PrestadoraSalud> obtenerPrestadorasSalud() {
		return prestadorBeanRemote.obtenerPrestadorasSalud();
	}
	
	public static boolean actualizarPrestador(PrestadoraSalud prestadoraSalud) {
		return prestadorBeanRemote.actualizarPrestador(prestadoraSalud);
	}
	
	private static PrestadorBeanRemote lookupRemotePrestadorBean(){
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");  
		props.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		
		Context ctx;
		try {
			ctx = new InitialContext(props);
			
			String jndiName = "ejb:viruscontrol/viruscontrol-ejb/PrestadorBean!uy.viruscontrol.bussines.interfaces.PrestadorBeanRemote?stateful";
			return (PrestadorBeanRemote)ctx.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
