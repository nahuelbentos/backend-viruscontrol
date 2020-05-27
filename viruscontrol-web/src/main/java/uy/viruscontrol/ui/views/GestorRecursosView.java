package uy.viruscontrol.ui.views;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import uy.viruscontrol.controllers.EnfermedadBeanController;
import uy.viruscontrol.model.entities.TipoRecurso;

@Named("GestorRecursosView")
@RequestScoped
public class GestorRecursosView {

	// Datos para la vista
	private String mensajeRecurso;
	private String mensajeTipoRecurso;
	
	// Datos negocio
	private String nombreTipoRecurso;
	private String descripcionTipoRecurso;
	private String nombreRecurso;
	private List<String> tiposDeRecursos;
	private String nombreTipoRecursoDropDown;

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

	}

	
	
	public String getMensajeRecurso() {
		return mensajeRecurso;
	}

	public void setMensajeRecurso(String mensajeRecurso) {
		this.mensajeRecurso = mensajeRecurso;
	}

	public String getMensajeTipoRecurso() {
		return mensajeTipoRecurso;
	}

	public void setMensajeTipoRecurso(String mensajeTipoRecurso) {
		this.mensajeTipoRecurso = mensajeTipoRecurso;
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

	public void agregarNuevoTipoRecurso() {
		boolean ok = EnfermedadBeanController.altaTipoRecurso(nombreTipoRecurso, descripcionTipoRecurso);

		if (ok) {
			this.mensajeTipoRecurso = "El Tipo de Recurso " + this.getNombreTipoRecurso() + " fue dado de alta.";
		} else {
			this.mensajeTipoRecurso = "Error, no se pudo dar de alta el Tipo de Recurso, verifique.";
		}
	}

	public void agregarNuevoRecurso() {

		int idAux = EnfermedadBeanController.getIdTipoRecursoByName(nombreTipoRecursoDropDown);

		boolean ok = EnfermedadBeanController.altaRecursoDeUnDeterminadoTipo(nombreRecurso, idAux);
		if (ok) {
			this.mensajeRecurso = "El Recurso " + this.getNombreRecurso() + " fue dado de alta.";
		} else {
			this.mensajeRecurso = "Error, no se pudo dar de alta el Recurso, verifique.";
		}
	}

}
