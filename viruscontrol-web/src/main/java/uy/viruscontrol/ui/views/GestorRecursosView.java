package uy.viruscontrol.ui.views;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import uy.viruscontrol.controllers.EnfermedadBeanController;
import uy.viruscontrol.controllers.ProveedorBeanController;
import uy.viruscontrol.model.entities.ProveedorRecursos;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.TipoRecurso;

@Named("GestorRecursosView")
@ApplicationScoped 
public class GestorRecursosView {


	
	// Datos negocio
	private String nombreTipoRecurso;
	private String descripcionTipoRecurso;
	private String nombreRecurso;
	private List<String> tiposDeRecursos;
	private String nombreTipoRecursoDropDown;
	private String codigoPeriferico;
	private String provRecSeleccionado;
	private String codigoRecursoPeriferico;
	
	private List<TipoRecurso> tiposRecursosPeriferico;
	private List<Recurso> recursosPeriferico;
	private List <ProveedorRecursos> proveedoresRecursos;
	private List<Recurso> recursosDisponiblesProvPeriferico;
	
	public GestorRecursosView() {
		super();
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

		tiposDeRecursos = new ArrayList<String>();
		for (TipoRecurso tipo : EnfermedadBeanController.obtenerTiposDeRecursos()) {
			tiposDeRecursos.add(tipo.getNombre());
		}

		tiposRecursosPeriferico = new ArrayList<TipoRecurso>();
		tiposRecursosPeriferico = ProveedorBeanController.obtenerTiposRecursosPeriferico();
		/*
		recursosPeriferico = new ArrayList<Recurso>();
		recursosPeriferico = ProveedorBeanController.obtenerRecursosPeriferico();
		*/
		proveedoresRecursos = new ArrayList<ProveedorRecursos>();
		proveedoresRecursos = ProveedorBeanController.obtenerProveedoresRecursos();
		
		recursosDisponiblesProvPeriferico = new ArrayList<Recurso>();
	}

	
	
	
	public String getNombreTipoRecurso() {
		return nombreTipoRecurso;
	}

	public void setNombreTipoRecurso(String nombreTipoRecurso) {
		this.nombreTipoRecurso = nombreTipoRecurso;
	}

	public String getDescripcionTipoRecurso() {
		return descripcionTipoRecurso;
	}

	public void setDescripcionTipoRecurso(String descripcionTipoRecurso) {
		this.descripcionTipoRecurso = descripcionTipoRecurso;
	}

	public String getNombreRecurso() {
		return nombreRecurso;
	}

	public void setNombreRecurso(String nombreRecurso) {
		this.nombreRecurso = nombreRecurso;
	}

	public List<String> getTiposDeRecursos() {
		return tiposDeRecursos;
	}

	public void setTiposDeRecursos(List<String> tiposDeRecursos) {
		this.tiposDeRecursos = tiposDeRecursos;
	}

	public String getNombreTipoRecursoDropDown() {
		return nombreTipoRecursoDropDown;
	}

	public void setNombreTipoRecursoDropDown(String nombreTipoRecursoDropDown) {
		this.nombreTipoRecursoDropDown = nombreTipoRecursoDropDown;
	}
	
	

	public String getCodigoPeriferico() {
		return codigoPeriferico;
	}

	public void setCodigoPeriferico(String codigoPeriferico) {
		this.codigoPeriferico = codigoPeriferico;
	}

	public List<TipoRecurso> getTiposRecursosPeriferico() {
		return tiposRecursosPeriferico;
	}

	public void setTiposRecursosPeriferico(List<TipoRecurso> tiposRecursosPeriferico) {
		this.tiposRecursosPeriferico = tiposRecursosPeriferico;
	}
	
	

	public List<Recurso> getRecursosPeriferico() {
		return recursosPeriferico;
	}

	public void setRecursosPeriferico(List<Recurso> recursosPeriferico) {
		this.recursosPeriferico = recursosPeriferico;
	}
	
	

	public String getProvRecSeleccionado() {
		return provRecSeleccionado;
	}

	public void setProvRecSeleccionado(String provRecSeleccionado) {
		this.provRecSeleccionado = provRecSeleccionado;
	}

	public List<ProveedorRecursos> getProveedoresRecursos() {
		return proveedoresRecursos;
	}

	public void setProveedoresRecursos(List<ProveedorRecursos> proveedoresRecursos) {
		this.proveedoresRecursos = proveedoresRecursos;
	}
	
	
	
	public String getCodigoRecursoPeriferico() {
		return codigoRecursoPeriferico;
	}

	public void setCodigoRecursoPeriferico(String codigoRecursoPeriferico) {
		this.codigoRecursoPeriferico = codigoRecursoPeriferico;
	}
	
	
	
	public List<Recurso> getRecursosDisponiblesProvPeriferico() {
		return recursosDisponiblesProvPeriferico;
	}

	public void setRecursosDisponiblesProvPeriferico(List<Recurso> recursosDisponiblesProvPeriferico) {
		this.recursosDisponiblesProvPeriferico = recursosDisponiblesProvPeriferico;
	}

	
	
	
	public void onProvRecDropDownChange() {
		if(this.provRecSeleccionado != null) {
			recursosDisponiblesProvPeriferico = ProveedorBeanController.obtenerRecursosDisponiblesProvPeriferico(provRecSeleccionado);
			//System.out.println("aca llega 1"+	codigoRecursoPeriferico);
		}else {
			//System.out.println("aca llega 2"+	codigoRecursoPeriferico);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("No se pudo cargar la lista de Recursos del proveedor, verifique."));
		}
	}

	

	public void agregarNuevoTipoRecurso() {
		boolean ok = EnfermedadBeanController.altaTipoRecurso(nombreTipoRecurso, descripcionTipoRecurso, codigoPeriferico);

		if (ok) {
			
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("El Tipo de Recurso " + this.getNombreTipoRecurso() + " fue dado de alta."));
		} else {
			
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error, no se pudo dar de alta el Tipo de Recurso, verifique."));
		}
		
		this.cleanForm();
		
	}

	public void agregarNuevoRecurso() {
		System.out.println("aca llega 3"+	codigoRecursoPeriferico);
		int idAux = EnfermedadBeanController.getIdTipoRecursoByName(nombreTipoRecursoDropDown);

		boolean ok = EnfermedadBeanController.altaRecursoDeUnDeterminadoTipo(nombreRecurso, idAux, codigoRecursoPeriferico);
		if (ok) {
			
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("El Recurso " + this.getNombreRecurso() + " fue dado de alta."));
		} else {
			
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error, no se pudo dar de alta el Recurso, verifique."));
		}
		
		this.cleanForm();
	}
	
	public void cleanForm() {
		
		setNombreTipoRecurso(null);
		setDescripcionTipoRecurso(null);
		setNombreRecurso(null);
		setNombreTipoRecursoDropDown(null);
		setCodigoPeriferico(null);
		setProvRecSeleccionado(null);
		//setCodigoRecursoPeriferico(null);
	}

}
