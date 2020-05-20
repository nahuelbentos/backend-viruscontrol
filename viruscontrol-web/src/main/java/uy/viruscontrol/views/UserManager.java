package uy.viruscontrol.views;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

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
	
	public Usuario getCurrentUser() {
		return currentUser;
	}

	public boolean isLoggedIn() {
		return currentUser != null;
	}
	
	public Map<String,String> getOpciones(){
		TreeMap<String,String> opciones = new TreeMap<String,String>();
		
		opciones.put("Inicio","home.xhtml");
		if (currentUser != null) {
			if (currentUser instanceof Administrador) {
				opciones.put("Gestión de usuarios","admin/gestorUsuarios.xhtml");
				opciones.put("Gestión de enfermedades","admin/gestorEnfermedad.xhtml");
				opciones.put("Gestión de fuente de datos","admin/gestorFuenteDatos.xhtml");
				opciones.put("Gestión de nodos periféricos","admin/gestorNodos.xhtml");
			} else {
				if (currentUser instanceof Gerente) {
					opciones.put("Gestión de enfermedades","gerente/gestorEnfermedad.xhtml");
					opciones.put("Fuente de datos","gerente/gestorFuenteDatos.xhtml");
					opciones.put("Generador de casos","gerente/gestorCasos.xhtml");
					opciones.put("Gestión de notificaciones","gerente/gestorNotificaciones.xhtml");
					opciones.put("Gráficas","gerente/charts.xhtml");
				}
			}
		}
				
		return opciones;
	}
	
	
}
