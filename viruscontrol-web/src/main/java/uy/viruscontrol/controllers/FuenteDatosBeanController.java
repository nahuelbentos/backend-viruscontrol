package uy.viruscontrol.controllers;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.viruscontrol.bussines.interfaces.FuenteDatosBeanRemote;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.FuenteDeDatos;
import uy.viruscontrol.model.entities.FuenteDeDatosEnfermedad;

public class FuenteDatosBeanController {

	
	private static FuenteDatosBeanRemote fuenteDatosBeanRemote = lookupRemoteFuenteDatosBean();
	
	
	public static boolean crearFuenteDatos(String codigo, String url) {
		return fuenteDatosBeanRemote.crearFuenteDatos(codigo, url);
	}
	
	public static  boolean actualizarFuenteDatos(FuenteDeDatos fuenteDatos) {
		return fuenteDatosBeanRemote.actualizarFuenteDatos(fuenteDatos);
	}

	public static List<FuenteDeDatos> obtenerFuenteDeDatos(){
		return fuenteDatosBeanRemote.obtenerFuenteDeDatos();
	}

	public static boolean eliminarFuenteDeDatos(FuenteDeDatos fuenteDatos) {
		return fuenteDatosBeanRemote.eliminarFuenteDeDatos(fuenteDatos);
	}
	
	public static List<FuenteDeDatosEnfermedad> obtenerTodosFuenteDeDatosEnfermedad() {
		return fuenteDatosBeanRemote.obtenerTodosFuenteDeDatosEnfermedad();
	}
	
	public static List<FuenteDeDatosEnfermedad> obtenerFuentesDeDatosEnfermedad(Enfermedad enfermedad){
		return fuenteDatosBeanRemote.obtenerFuentesPorEnfermedad(enfermedad);
	}
	
	public static boolean crearFuenteDeDatosEnfermedad(FuenteDeDatosEnfermedad fuenteEnfermedad) {
		return fuenteDatosBeanRemote.crearFuenteParaEnfermedad(fuenteEnfermedad);
	}

	public static boolean eliminarFuenteDeDatosEnfermedad(int idEliminar) {
		return fuenteDatosBeanRemote.eliminarFuenteDeDatosEnfermedad(idEliminar);
	}
	
	private static FuenteDatosBeanRemote lookupRemoteFuenteDatosBean(){
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");  
		props.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		
		Context ctx;
		try {
			ctx = new InitialContext(props);
			
			String jndiName = "ejb:viruscontrol/viruscontrol-ejb/FuenteDatosBean!uy.viruscontrol.bussines.interfaces.FuenteDatosBeanRemote";
			return (FuenteDatosBeanRemote)ctx.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
