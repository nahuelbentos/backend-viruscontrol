package uy.viruscontrol.controllers;

import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.viruscontrol.bussines.interfaces.UsuarioBeanRemote;
import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.Usuario;

public class UsuarioBeanController {

	private static UsuarioBeanRemote iUsuarioBean = lookupRemoteUsuarioBean();
	
	public static boolean registrarUsuario(Usuario user) {
		return iUsuarioBean.registrarUsuario(user);
	}
	
	public static List<Ciudadano> mostrarCiudadanos(){
		return iUsuarioBean.mostrarCiudadanos();
	}
	
	public static List<Medico> mostrarMedicos() {
		return iUsuarioBean.mostrarMedicos();
	}
	
	public static List<Gerente> mostrarGerentes(){
		return iUsuarioBean.mostrarGerentes();
	}
	public static List<Administrador> mostrarAdministradores(){
		return iUsuarioBean.mostrarAdministradores();
	}
	public static void editarCiudadano(int ciudadanoId,String nombre,String apellido, String correo,String direccion,String nacionalidad,String userName,Calendar fecha) {
		 iUsuarioBean.editarCiudadano(ciudadanoId, nombre, apellido, correo, direccion, nacionalidad, userName, fecha);
	}
	
	public static void editarMedico(int medicoId,String nombre,String apellido, String correo,String direccion,String nacionalidad,String userName,Calendar fecha) {
		 iUsuarioBean.editarMedico(medicoId, nombre, apellido, correo, direccion, nacionalidad, userName, fecha);
	}
	public static void editarGerente(int gerenteId,String nombre,String apellido, String correo,String direccion,String nacionalidad,String userName,Calendar fecha,String password) {
		 iUsuarioBean.editarGerente(gerenteId, nombre, apellido, correo, direccion, nacionalidad, userName, fecha, password);
	}
	public static void editarAdmin(int adminId,String nombre,String apellido, String correo,String direccion,String nacionalidad,String userName,Calendar fecha,String password) {
		 iUsuarioBean.editarAdmin(adminId, nombre, apellido, correo, direccion, nacionalidad, userName, fecha, password);
	}
	
	public static boolean eliminarUsuario(Usuario user) {
		return iUsuarioBean.eliminarUsuario(user);
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
