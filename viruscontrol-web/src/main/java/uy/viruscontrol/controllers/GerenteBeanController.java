package uy.viruscontrol.controllers;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.viruscontrol.bussines.interfaces.EnfermedadBeanRemote;
import uy.viruscontrol.bussines.interfaces.GerenteBeanRemote;
import uy.viruscontrol.model.entities.Caso;

public class GerenteBeanController {

	private static GerenteBeanRemote iGerenteBean = lookupRemoteGerenteBean();
	
	public static List<Caso> obtenerCasos(){
		return iGerenteBean.obtenerCasos();
		
	}
	
	
	
	private static GerenteBeanRemote lookupRemoteGerenteBean(){
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");  
		props.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		
		Context ctx;
		try {
			ctx = new InitialContext(props);
			
			String jndiName = "ejb:viruscontrol/viruscontrol-ejb/GerenteBean!uy.viruscontrol.bussines.interfaces.GerenteBeanRemote?stateful";
			return (GerenteBeanRemote)ctx.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
