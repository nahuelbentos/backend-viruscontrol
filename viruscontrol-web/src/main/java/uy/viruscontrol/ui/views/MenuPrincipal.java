package uy.viruscontrol.ui.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import uy.viruscontrol.bussines.interfaces.GerenteBeanLocal;
import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Gerente;

@Named("MenuPrincipal")
@SessionScoped
public class MenuPrincipal implements Serializable {
	
	private static final long serialVersionUID = 8592263321495581704L;

	@Inject private UserManager userManage;
	
	@Inject private GerenteBeanLocal gerenteEjb;
	
	public UserManager getUserManage() {
		return userManage;
	}
	public MenuPrincipal(UserManager userManage) {
		super();
		this.userManage = userManage;
	}
	public MenuPrincipal() {
		super();
	}
	
	public List<List<String>> getWidget(){
		
		List<List<String>> widgets = new ArrayList<List<String>>();
		
		if (userManage.getCurrentUser() instanceof Gerente) {
			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance() 
				      .getExternalContext().getContext(); 
			String gerentePath = ctx.getContextPath()+"/gerente/";
			
			
			List<String> widget1 = new ArrayList<String>();
			String titulo = "Gestionar notificaciones";
			widget1.add(titulo);
			String cuerpo = "Configuración sobre que usuario recibe las notificaciones de VirusControl UY";
			widget1.add(cuerpo);
			String link = gerentePath+"gestorNotificaciones.xhtml@Ir a gestión";
			widget1.add(link);
			
			widgets.add(widget1);
			
			
			List<String> widget2 = new ArrayList<String>();
			titulo = "Reporte de casos";
			widget2.add(titulo);
			cuerpo = "Actualmente se encuentran " + gerenteEjb.obtenerCasos().size() + " casos activos en VirusControl UY";
			widget2.add(cuerpo);
			link = gerentePath+"reporteCasos.xhtml@Ver casos";
			widget2.add(link);
			
			widgets.add(widget2);
		}
		if (userManage.getCurrentUser() instanceof Administrador) {
			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance() 
				      .getExternalContext().getContext(); 
			String gerentePath = ctx.getContextPath()+"/admin/";
			
			
			List<String> widget1 = new ArrayList<String>();
			String titulo = "Fuente de datos";
			widget1.add(titulo);
			String cuerpo = "Incorpore nuevas fuentes de datos para el sistema";
			widget1.add(cuerpo);
			String link = gerentePath+"gestorFuenteDatos.xhtml@Ver más";
			widget1.add(link);
			
			widgets.add(widget1);
			
			
			List<String> widget2 = new ArrayList<String>();
			titulo = "Nodos perifericos";
			widget2.add(titulo);
			cuerpo = "Agregue nuevos perifericos al sistema";
			widget2.add(cuerpo);
			link = gerentePath+"gestorNodos.xhtml@Trabajar con periféricos";
			widget2.add(link);
			
			widgets.add(widget2);
			
			List<String> widget3 = new ArrayList<String>();
			titulo = "Usuarios del sistema";
			widget3.add(titulo);
			cuerpo = "Gestionar usuarios del sistema. Ciudadanos, medicos, gerentes y administradores";
			widget3.add(cuerpo);
			link = gerentePath+"gestorUsuarios.xhtml@Usuarios del sistema";
			widget3.add(link);
			
			widgets.add(widget3);
			
		}
		
		return widgets;
		
	}
	
}
