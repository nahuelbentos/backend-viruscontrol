package uy.viruscontrol.controllers;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.viruscontrol.bussines.interfaces.EnfermedadBeanRemote;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.Sintoma;
import uy.viruscontrol.model.entities.TipoRecurso;

public class EnfermedadBeanController {

	private static EnfermedadBeanRemote iEnfermedadBean = lookupRemoteEnfermedadBean();
	
	
	//Operaciones
	//Enfermedades
	public static boolean crearEnfermedadInfecciosa(String nombreEnfermedad, String nombreTipoEnfermedad, String nombreAgente, List<Sintoma> sintomas) {
		return iEnfermedadBean.crearEnfermedadInfecciosa(nombreEnfermedad, nombreTipoEnfermedad, nombreAgente, sintomas, false);
	}
	
	
	public static boolean asociarRecursoRecomendado(String nombreEnfermedad, String nombreRecurso, boolean recursoTrata, boolean recursoPreviene) {
			return iEnfermedadBean.asociarRecursoRecomendado(nombreEnfermedad, nombreRecurso, recursoTrata, recursoPreviene);
	}
	
	public static List<Enfermedad> obtenerEnfermedadesNoAprobadas() {
		return iEnfermedadBean.obtenerListaEnfermedadesNoAprobadas();
	}
	
	public static List<Enfermedad> obtenerEnfermedades() {
		return iEnfermedadBean.obtenerEnfermedades();
	}
	
	public static boolean aprobarEnfermedad(int idEnfermedad) {
		return iEnfermedadBean.aprobarEnfermedadInfecciosa(idEnfermedad);
	}
	
	public static int getIdEnfermedadByName(String nombreEnfermedad) {
		return iEnfermedadBean.getIdEnfermedadByName(nombreEnfermedad);
	}
	
	//Recursos y Tipos de Recursos
	public static boolean altaTipoRecurso(String nombre, String descripcion) {
		return iEnfermedadBean.altaTipoRecurso(nombre, descripcion);
	}
	
	public static boolean altaRecursoDeUnDeterminadoTipo(String nombre, int idTipoRecurso) {
		
		return iEnfermedadBean.altaRecursoDeUnDeterminadoTipo(nombre, idTipoRecurso);
	}
	
	public static List<TipoRecurso> obtenerTiposDeRecursos(){
		return iEnfermedadBean.obtenerTiposDeRecursos();
	}
	
	public static int getIdTipoRecursoByName(String nombreTipoRecurso) {
		return iEnfermedadBean.getIdTipoRecursoByName(nombreTipoRecurso);
	}
	
	public static List<Recurso> obtenerRecursos() {
		return iEnfermedadBean.obtenerRecursos();
	}
	
	//LOOKUP
	
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
