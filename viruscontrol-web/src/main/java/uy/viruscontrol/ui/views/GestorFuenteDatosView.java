package uy.viruscontrol.ui.views;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import uy.viruscontrol.controllers.FuenteDatosBeanController;

@Named("GestorFuenteDatosView")
@RequestScoped
public class GestorFuenteDatosView implements Serializable{

	
	private static final long serialVersionUID = 4495934608841731944L;

	//Datos alta
	private String codigo;
	private String url;
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	public void agregarFuenteDatos() {
		
		boolean ok = FuenteDatosBeanController.crearFuenteDatos(codigo, url);
		if (ok) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Fuente de Datos con codigo "+ this.codigo +" creada."));
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error!, Fuente de Datos no creada, verifique"));
		}
	}
	
}
