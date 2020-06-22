package uy.viruscontrol.ui.views;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.bussines.interfaces.SessionBeanLocal;
import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.entities.Usuario;

@Named("UserManager")
@SessionScoped
public class UserManager implements Serializable {
	private static final long serialVersionUID = -4166151468721069760L;
	
	private String username;
	private String sessionToken;
	private String password;
	private String mensaje;
	
	private Usuario currentUser;
	
	@Inject private SessionBeanLocal sessionEjb;
	
	
	// Acceso a la sesión desde el facelet
	private static HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

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
		AuthResponse res = sessionEjb.iniciarSesion(username, password);
		if (res.equals(AuthResponse.OK)) {
			this.sessionToken = sessionEjb.getTokenByUsername(username);
			currentUser = sessionEjb.getUsuarioLogueado(sessionToken);
			if(session.getAttribute("currentUser")==null) {
				System.out.println("current user es null");
				  
			} else { 
				System.out.println("current user no es null");
				session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			}

			// guardo el usuario logueado en sesión
			session.setAttribute("currentUser", currentUser);
			return "exito";
		} else {
			this.mensaje = "Usuario inexistente o las credenciales son incorrectas.";
			return "fallo";
			
		}
	}
	
	public String logout() {
		sessionEjb.cerrarSesion(this.sessionToken);
		currentUser = null;
		session.removeAttribute("currentUser");
		
		HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String redirectUri = "login.xhtml";
		if (origRequest.getRequestURI().contains("admin") || origRequest.getRequestURI().contains("gerente"))
			redirectUri = "../"+redirectUri;
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUri);
			FacesContext.getCurrentInstance().responseComplete();
			return "login";
		} catch (IOException e) {
			//e.printStackTrace();
			return "login";
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
					opciones.put("Gestión de notificaciones",getDirVirtual(currentUser)+"gestorNotificaciones.xhtml");
					opciones.put("Gráficas",getDirVirtual(currentUser)+"charts.xhtml");
				}
			}
		}
				
		return opciones;
	}
	
	
	/* Metodo auxiliar que devuelve el directorio del usuario logueado, 
	 * en caso que me encuentre dentro del directorio, le devuelvo empty. */
	public static String getDirVirtual(Usuario currentUser) {
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
	
	public String getHome() {
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance() 
			      .getExternalContext().getContext(); 
			String realPath = ctx.getContextPath()+"/home.xhtml";
		return realPath;
	}
	
	public String getTipoUsuario() {
		return currentUser.getClass().getName().substring(currentUser.getClass().getName().lastIndexOf(".")+1, currentUser.getClass().getName().length());
	}
}
