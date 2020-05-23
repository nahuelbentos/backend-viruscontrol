package uy.viruscontrol.controllers;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.viruscontrol.bussines.interfaces.EnfermedadBeanRemote;
import uy.viruscontrol.model.entities.Sintoma;

public class EnfermedadBeanController {

	private static EnfermedadBeanRemote iEnfermedadBean = lookupRemoteEnfermedadBean();
	
	public static boolean crearEnfermedadInfecciosa(String nombreEnfermedad, String nombreTipoEnfermedad, String nombreAgente, List<Sintoma> sintomas) {
		return iEnfermedadBean.crearEnfermedadInfecciosa(nombreEnfermedad, nombreTipoEnfermedad, nombreAgente, sintomas, false);
	}
	
	public static int altaRecursoRecomendado(String nombreEnfermedad, String nombreRecurso, boolean recursoTrata, boolean recursoPreviene) {
			return iEnfermedadBean.altaRecursoRecomendado(nombreEnfermedad, nombreRecurso, recursoTrata, recursoPreviene);
	}
	
	private static EnfermedadBeanRemote lookupRemoteEnfermedadBean(){
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");  
		props.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		
		Context ctx;
		try {
			ctx = new InitialContext(props);
			
			String jndiName = "ejb:viruscontrol/viruscontrol-ejb/EnfermedadBean!uy.viruscontrol.bussines.interfaces.EnfermedadBeanRemote?stateful";
			return (EnfermedadBeanRemote)ctx.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
