package uy.viruscontrol.ui.views;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import uy.viruscontrol.controllers.PrestadorBeanController;

@Named("GestorPrestadorView")
@RequestScoped
public class GestorPrestadorView implements Serializable{
	private static final long serialVersionUID = 1L;
	
		
	private String mensaje;
	
	private String nombrePrestador;

	public GestorPrestadorView() {
		super();
		// TODO Auto-generated constructor stub
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
			this.mensaje = "El Prestador de Salud" + this.getNombrePrestador() + "se creó con éxito";
		}
		else {
			this.mensaje = "Error Prestador no creado, verifique";
		}
	}
}
