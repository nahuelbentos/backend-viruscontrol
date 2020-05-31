package uy.viruscontrol.ui.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import uy.viruscontrol.controllers.PrestadorBeanController;
import uy.viruscontrol.controllers.ProveedorBeanController;
import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.entities.PrestadoraSalud;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.model.entities.ProveedorRecursos;

@Named("GestorPrestadorView")
@RequestScoped
public class GestorPrestadorView implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private UserManager userManager;
		
	private String mensaje;
	
	//Datos Alta
	private String nombrePrestador;
	List<PrestadoraSalud> prestadorasSalud;

	private PrestadoraSalud prestadora;
	
	//Datos Eliminar
	private List<PrestadoraSalud> prestadorasEliminadas = new ArrayList<>();

	@PostConstruct
	public void init() {
		prestadorasSalud = new ArrayList<PrestadoraSalud>();
		prestadorasSalud = PrestadorBeanController.obtenerPrestadorasSalud();
		
		
	}
	
	
	public PrestadoraSalud getPrestadora() {
		return prestadora;
	}

	public void setPrestadora(PrestadoraSalud prestadora) {
		this.prestadora = prestadora;
	}

	
	public List<PrestadoraSalud> getPrestadorasSalud() {
		return prestadorasSalud;
	}

	public void setPrestadorasSalud(List<PrestadoraSalud> prestadorasSalud) {
		this.prestadorasSalud = prestadorasSalud;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getNombrePrestador() {
		return nombrePrestador;
	}

	public void setNombrePrestador(String nombrePrestador) {
		this.nombrePrestador = nombrePrestador;
	}
	
	
	
	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void agregarPrestador() {
		
		boolean ok = PrestadorBeanController.crearPrestadorSalud(this.nombrePrestador);
		if (ok) {
			this.mensaje = "El Prestador de Salud " + this.getNombrePrestador() + " se creó con éxito";
		}
		else {
			this.mensaje = "Error Prestador no creado, verifique";
		}
	}
	
	
	//ACTUALIZAR - METODO ACTUALIZAR AJAX EVENT DATATABLE
	public void actualizar(RowEditEvent event) {
		
		prestadora = (PrestadoraSalud) event.getObject();
		
		boolean ok = PrestadorBeanController.actualizarPrestador(prestadora);
		
		if (ok) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("El Prestador fue actualizado"));
			
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error!, Prestador no actualizado, verifique"));
			
		}
	}
	
	//ACTUALIZAR - METODO CANCELAR AJAX EVENT DATATABLE
	public void cancelar(RowEditEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Cancelado!"));
	}
	
	
	
	public Map<String,String> getOpciones(){
		TreeMap<String,String> opciones = new TreeMap<String,String>();
		
		if (userManager.getCurrentUser() != null) {
			if (userManager.getCurrentUser() instanceof Administrador) {
				opciones.put("ABM Prestadora de Salud", UserManager.getDirVirtual(userManager.getCurrentUser())+"gestorPrestadoraSalud.xhtml");
				opciones.put("ABM Proveedor Recurso y/o Exámen", UserManager.getDirVirtual(userManager.getCurrentUser())+"gestorProveedores.xhtml");
				
			} else {
				if (userManager.getCurrentUser() instanceof Gerente) {
					
					
				}
			}
		}
				
		return opciones;
	}
	
	//Metodo que elimina de la lista padre aquellos objetos seleccionados en la tabla
	public String eliminarPrestadoraSalud() {
		for(PrestadoraSalud ps : prestadorasSalud) {
			if(ps.isDeleted()) {
				prestadorasEliminadas.add(ps);
			}
		}
		if(!prestadorasEliminadas.isEmpty()) {
			prestadorasSalud.removeAll(prestadorasEliminadas);
			for(PrestadoraSalud pre : prestadorasEliminadas) {
				PrestadorBeanController.eliminarPrestadoraSalud(pre);
			}
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Proveedor Eliminado."));
		}
		
		
		return "gestorProveedores";
	}
	
}
