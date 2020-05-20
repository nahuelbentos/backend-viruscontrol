package uy.viruscontrol.ui.views;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.controllers.SessionBeanController;
import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.entities.Usuario;

@Named("UserManager")
@SessionScoped
public class UserManager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String mensaje;
	
	private Usuario currentUser;

	public UserManager() {
		super();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String login() {
		AuthResponse res = SessionBeanController.iniciarSesion(username, password);
		System.out.println("res->"+res);
		if (res.equals(AuthResponse.OK)) {
			currentUser = SessionBeanController.getUsuarioLogeado(username);
			System.out.println("usuario logueado: " + currentUser.getUsername());
			return "exito";
		} else {
			this.mensaje = "Usuario inexistente o las credenciales son incorrectas.";
			return "fallo";
			
		}
	}
	
	public String logout() {
		System.out.println("el nieri se quiere ir");
		return "login";
	}
	
	public Usuario getCurrentUser() {
		return currentUser;
	}

	public boolean isLoggedIn() {
		return currentUser != null;
	}
	
	public Map<String,String> getOpciones(){
		TreeMap<String,String> opciones = new TreeMap<String,String>();
		
		if (currentUser != null) {
			if (currentUser instanceof Administrador) {
				opciones.put("Gestión de usuarios",getDirVirtual(currentUser)+"gestorUsuarios.xhtml");
				opciones.put("Gestión de enfermedades",getDirVirtual(currentUser)+"gestorEnfermedad.xhtml");
				opciones.put("Gestión de fuente de datos",getDirVirtual(currentUser)+"gestorFuenteDatos.xhtml");
				opciones.put("Gestión de nodos periféricos",getDirVirtual(currentUser)+"gestorNodos.xhtml");
			} else {
				if (currentUser instanceof Gerente) {
					opciones.put("Gestión de enfermedades",getDirVirtual(currentUser)+"gestorEnfermedad.xhtml");
					opciones.put("Fuente de datos",getDirVirtual(currentUser)+"gestorFuenteDatos.xhtml");
					opciones.put("Generador de casos",getDirVirtual(currentUser)+"gestorCasos.xhtml");
					opciones.put("Gestión de notificaciones",getDirVirtual(currentUser)+"gestorNotificaciones.xhtml");
					opciones.put("Gráficas",getDirVirtual(currentUser)+"charts.xhtml");
				}
			}
		}
				
		return opciones;
	}
	
	
	/* Metodo auxiliar que devuelve el directorio del usuario logueado, 
	 * en caso que me encuentre dentro del directorio, le devuelvo empty. */
	private static String getDirVirtual(Usuario currentUser) {
		HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		if (origRequest.getRequestURI().contains("admin") || origRequest.getRequestURI().contains("gerente"))
			return "";
		
		if (currentUser instanceof Administrador) 
			return "admin/";
		else
			if (currentUser instanceof Gerente)
				return "gerente/";
		
		return "error";
		
		
	}
	
	
}
