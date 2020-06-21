package uy.viruscontrol.ui.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import uy.viruscontrol.bussines.enumerated.TipoNotificacion;
import uy.viruscontrol.bussines.interfaces.GerenteBeanLocal;
import uy.viruscontrol.model.entities.ConfiguracionNotificaciones;
import uy.viruscontrol.model.entities.FuenteDeDatos;

@Named("GestorNotificacionView")
@RequestScoped
public class GestorNotificacionView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3584912396466947273L;
	
	@Inject private GerenteBeanLocal gerenteBean;
	
	private List<ConfiguracionNotificaciones> confNotificaciones;
	
	private ConfiguracionNotificaciones confnot;
	
	private boolean boolnotificarGerente;
	
	private boolean boolnotificarMedico;
	
	private boolean boolnotificarCiudadano;
	
	@PostConstruct
	public void init() {
		confNotificaciones=gerenteBean.obtenerConfuracionNotificacion();
		
	}
	
	
	public void actualizar() {
	System.out.println("actualizando");
	for(ConfiguracionNotificaciones cn:confNotificaciones) {
		gerenteBean.configurarNotificacion(cn.isNotificarCiudadano(), cn.isNotificarMedico(), cn.isNotificarGerentes(), TipoNotificacion.CAMBIOESTADOCASO);
		
	}
	
	
	
	}
	
	public void cancelar(RowEditEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Cancelado!"));
	}
	
	public List<ConfiguracionNotificaciones> getConfNotificaciones() {
		return confNotificaciones;
	}

	public void setConfNotificaciones(List<ConfiguracionNotificaciones> confNotificaciones) {
		this.confNotificaciones = confNotificaciones;
	}


	public ConfiguracionNotificaciones getConfnot() {
		return confnot;
	}


	public void setConfnot(ConfiguracionNotificaciones confnot) {
		this.confnot = confnot;
	}


	public boolean isBoolnotificarGerente() {
		return boolnotificarGerente;
	}


	public void setBoolnotificarGerente(boolean boolnotificarGerente) {
		this.boolnotificarGerente = boolnotificarGerente;
	}


	public boolean isBoolnotificarMedico() {
		return boolnotificarMedico;
	}


	public void setBoolnotificarMedico(boolean boolnotificarMedico) {
		this.boolnotificarMedico = boolnotificarMedico;
	}


	public boolean isBoolnotificarCiudadano() {
		return boolnotificarCiudadano;
	}


	public void setBoolnotificarCiudadano(boolean boolnotificarCiudadano) {
		this.boolnotificarCiudadano = boolnotificarCiudadano;
	}


	

	
	

}
