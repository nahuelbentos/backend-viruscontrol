package uy.viruscontrol.controllers;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.recurso.model.entities.TipoRecurso;

import uy.viruscontrol.bussines.interfaces.ProveedorBeanRemote;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorRecursoRemote;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.model.entities.ProveedorRecursos;

public class ProveedorBeanController {

	private static ProveedorBeanRemote proveedorBeanRemote = lookupRemoteProveedorBean();
	private static ServiceAgentProveedorRecursoRemote serviceAgentProveedorRecursoRemote = lookupRemoteServiceAgentProveedorRecursos();
	
	public static boolean crearProveedor(int tipo,String nombreProveedor,String direccion,String ciudad,String barrio,String rangoHorario, String codigoPeriferico) {
		return proveedorBeanRemote.nuevoProveedor(tipo, nombreProveedor, direccion, ciudad, barrio, rangoHorario, codigoPeriferico);
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
	
	public static boolean eliminarProveedorRecursos(ProveedorRecursos proveedorRecurso) {
		return proveedorBeanRemote.eliminarProveedorRecursos(proveedorRecurso);
	}
	
	public static boolean eliminarProveedorExamenes(ProveedorExamen proveedorExamen) {
		return proveedorBeanRemote.eliminarProveedorExamenes(proveedorExamen);
	}
	
	public static List<ProveedorRecursos> obtenerProveedoresPeriferico(){
		return serviceAgentProveedorRecursoRemote.getProveedoresPeriferico();
	}
	
	public static List<uy.viruscontrol.model.entities.TipoRecurso> obtenerTiposRecursosPeriferico(){
		return serviceAgentProveedorRecursoRemote.getAllTipoDeRecursosPeriferico();
	}
	
	public static List<uy.viruscontrol.model.entities.Recurso> obtenerRecursosPeriferico(){
		return serviceAgentProveedorRecursoRemote.getAllRecursosPeriferico();
	}
	
	public static List<uy.viruscontrol.model.entities.Recurso> obtenerRecursosDisponiblesProvPeriferico(String codigoPeriferico){
		return serviceAgentProveedorRecursoRemote.getRecursosProvPeriferico(codigoPeriferico);
	}
	
	public static boolean altaRecursoProveedor(String codigoProveedor, String nombreRecurso) {
		return proveedorBeanRemote.altaRecursoProveedor(codigoProveedor, nombreRecurso);
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
	
	private static ServiceAgentProveedorRecursoRemote lookupRemoteServiceAgentProveedorRecursos(){
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");  
		props.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		
		Context ctx;
		try {
			ctx = new InitialContext(props);
			
			String jndiName = "ejb:viruscontrol/viruscontrol-ejb/ServiceAgentProveedorRecurso!uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorRecursoRemote";
			return (ServiceAgentProveedorRecursoRemote)ctx.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
