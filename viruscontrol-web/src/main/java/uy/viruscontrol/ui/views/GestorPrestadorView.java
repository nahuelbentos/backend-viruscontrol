package uy.viruscontrol.ui.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import uy.viruscontrol.controllers.PrestadorBeanController;
import uy.viruscontrol.model.entities.PrestadoraSalud;

@Named("GestorPrestadorView")
@RequestScoped
public class GestorPrestadorView implements Serializable{
	private static final long serialVersionUID = 1L;
	
		
	private String mensaje;
	
	
	private String nombrePrestador;
	List<PrestadoraSalud> prestadorasSalud;

	private PrestadoraSalud prestadora;
	
	private String nuevoNombrePrestadora;
	
	public GestorPrestadorView() {
		super();
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		prestadorasSalud = new ArrayList<PrestadoraSalud>();
		prestadorasSalud = PrestadorBeanController.obtenerPrestadorasSalud();
		
		prestadora = new PrestadoraSalud();
	}
	
	
	
	public String getNuevoNombrePrestadora() {
		return nuevoNombrePrestadora;
	}

	public void setNuevoNombrePrestadora(String nuevoNombrePrestadora) {
		this.nuevoNombrePrestadora = nuevoNombrePrestadora;
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
		prestadora.setNombre(nuevoNombrePrestadora);
		
		boolean ok = PrestadorBeanController.actualizarPrestador(prestadora);
		
		if (ok) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("El Prestador de Salud se actualizó con éxito"));
			
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error Prestador no actualizado, verifique"));
			
		}
	}
	
	//ACTUALIZAR - METODO CANCELAR AJAX EVENT DATATABLE
	public void cancelar(RowEditEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Cancelado!"));
	}
	
	
	
}
