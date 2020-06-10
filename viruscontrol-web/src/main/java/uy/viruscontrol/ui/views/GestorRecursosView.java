package uy.viruscontrol.ui.views;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import uy.viruscontrol.controllers.EnfermedadBeanController;
import uy.viruscontrol.controllers.ProveedorBeanController;
import uy.viruscontrol.model.entities.TipoRecurso;

@Named("GestorRecursosView")
@RequestScoped
public class GestorRecursosView {


	
	// Datos negocio
	private String nombreTipoRecurso;
	private String descripcionTipoRecurso;
	private String nombreRecurso;
	private List<String> tiposDeRecursos;
	private String nombreTipoRecursoDropDown;
	private String codigoPeriferico;
	
	private List<TipoRecurso> tiposRecursosPeriferico;
	
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

		int idAux = EnfermedadBeanController.getIdTipoRecursoByName(nombreTipoRecursoDropDown);

		boolean ok = EnfermedadBeanController.altaRecursoDeUnDeterminadoTipo(nombreRecurso, idAux);
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
	}

}
