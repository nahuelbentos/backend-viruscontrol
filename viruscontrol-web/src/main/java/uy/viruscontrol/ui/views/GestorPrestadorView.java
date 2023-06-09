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

import uy.viruscontrol.bussines.interfaces.PrestadorBeanLocal;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentPrestadoraSaludLocal;
import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.entities.PrestadoraSalud;

@Named("GestorPrestadorView")
@RequestScoped
public class GestorPrestadorView implements Serializable{
	
	private static final long serialVersionUID = -5225955974733127158L;
	
	@Inject private PrestadorBeanLocal prestadorEjb;
	@Inject private ServiceAgentPrestadoraSaludLocal sagRucaf;
	
	@Inject
	private UserManager userManager;
		
	//Datos Alta
	private String nombrePrestador;
	private List<PrestadoraSalud> prestadorasSalud;
	private List<PrestadoraSalud> prestadorasRucaf;
	private int idRucaf;
	private PrestadoraSalud prestadora;
	
	//Datos Eliminar
	private List<PrestadoraSalud> prestadorasEliminadas = new ArrayList<>();

	@PostConstruct
	public void init() {
		prestadorasSalud = new ArrayList<PrestadoraSalud>();
		prestadorasSalud = prestadorEjb.obtenerPrestadorasSalud();
		
		prestadorasRucaf = new ArrayList<PrestadoraSalud>();
		prestadorasRucaf = sagRucaf.obtenerPrestadorasRucaf();
		
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
	
	


	public List<PrestadoraSalud> getPrestadorasRucaf() {
		return prestadorasRucaf;
	}


	public void setPrestadorasRucaf(List<PrestadoraSalud> prestadorasRucaf) {
		this.prestadorasRucaf = prestadorasRucaf;
	}


	public String getNombrePrestador() {
		return nombrePrestador;
	}

	public void setNombrePrestador(String nombrePrestador) {
		this.nombrePrestador = nombrePrestador;
	}
	
	
	public int getIdRucaf() {
		return idRucaf;
	}


	public void setIdRucaf(int idRucaf) {
		this.idRucaf = idRucaf;
	}


	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void agregarPrestador() {
		
		boolean ok = prestadorEjb.nuevoPrestador(this.nombrePrestador, this.idRucaf);
		if (ok) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Prestadora de nombre "+ this.nombrePrestador +" creada."));
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error, Prestador no creado, verifique."));
		}
		
		this.cleanForm();
	}
	
	
	//ACTUALIZAR - METODO ACTUALIZAR AJAX EVENT DATATABLE
	public void actualizar(RowEditEvent event) {
		
		prestadora = (PrestadoraSalud) event.getObject();
		
		boolean ok = prestadorEjb.actualizarPrestador(prestadora);
		
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
				prestadorEjb.eliminarPrestadoraSalud(pre);
			}
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Proveedor Eliminado."));
		}
		
		
		return "gestorPrestadoraSalud";
	}
	
	public void cleanForm() {
		setIdRucaf(0);
		setNombrePrestador(null);
	}
}
