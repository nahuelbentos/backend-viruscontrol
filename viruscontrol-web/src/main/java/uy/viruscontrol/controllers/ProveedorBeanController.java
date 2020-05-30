package uy.viruscontrol.controllers;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import uy.viruscontrol.bussines.interfaces.ProveedorBeanRemote;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.model.entities.ProveedorRecursos;

public class ProveedorBeanController {

private static ProveedorBeanRemote proveedorBeanRemote = lookupRemoteProveedorBean();
	
	public static boolean crearProveedor(int tipo, String nombreProveedor, String direccion, String barrio, String rangoHorario) {
		return proveedorBeanRemote.nuevoProveedor(tipo, nombreProveedor, direccion, barrio, rangoHorario);
	}
	
	public static List<ProveedorExamen> obtenerProveedoresExamenes(){
		return proveedorBeanRemote.obtenerProveedoresExamenes();
	}
	
	public static List<ProveedorRecursos> obtenerProveedoresRecursos(){
		return proveedorBeanRemote.obtenerProveedoresRecursos();
	}
	
	public static boolean actualizarProveedorExamen(ProveedorExamen proveedorExamen) {
		return proveedorBeanRemote.actualizarProveedorExamen(proveedorExamen);
	}
	
	public static boolean actualizarProveedorRecurso(ProveedorRecursos proveedorRecurso) {
		return proveedorBeanRemote.actualizarProveedorRecursos(proveedorRecurso);
	}
	
	
	
	private static ProveedorBeanRemote lookupRemoteProveedorBean(){
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");  
		props.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		
		Context ctx;
		try {
			ctx = new InitialContext(props);
			
			String jndiName = "ejb:viruscontrol/viruscontrol-ejb/ProveedorBean!uy.viruscontrol.bussines.interfaces.ProveedorBeanRemote";
			return (ProveedorBeanRemote)ctx.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
