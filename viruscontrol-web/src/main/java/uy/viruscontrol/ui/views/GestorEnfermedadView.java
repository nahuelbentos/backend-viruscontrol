package uy.viruscontrol.ui.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import uy.viruscontrol.controllers.EnfermedadBeanController;
import uy.viruscontrol.model.entities.Sintoma;

@Named("GestorEnfermedadView")
@RequestScoped
public class GestorEnfermedadView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserManager userManage;
	// Datos para la vista
	private String mensaje;
	
	// Datos del negocio
	private String nombreEnfermedad;
	private String nombreTipoEnfermedad;
	private String nombreAgente;
	private List<Sintoma> sintomas;
	private String sintomasStr;
	
	public GestorEnfermedadView() {
		super();
	}
	public UserManager getUserManage() {
		return userManage;
	}
	public void setUserManage(UserManager userManage) {
		this.userManage = userManage;
	}
	public String getNombreEnfermedad() {
		return nombreEnfermedad;
	}
	public void setNombreEnfermedad(String nombreEnfermedad) {
		this.nombreEnfermedad = nombreEnfermedad;
	}
	public String getNombreTipoEnfermedad() {
		return nombreTipoEnfermedad;
	}
	public void setNombreTipoEnfermedad(String nombreTipoEnfermedad) {
		this.nombreTipoEnfermedad = nombreTipoEnfermedad;
	}
	public String getNombreAgente() {
		return nombreAgente;
	}
	public void setNombreAgente(String nombreAgente) {
		this.nombreAgente = nombreAgente;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getSintomasStr() {
		return sintomasStr;
	}
	public void setSintomasStr(String sintomasStr) {
		this.sintomasStr = sintomasStr;
	}
	
	public void agregarNuevaEnfermedad() {
		this.sintomas = new ArrayList<Sintoma>();
		String[] ss = this.sintomasStr.split(",");
		for(int i = 0; i < ss.length; i++) {
			Sintoma s = new Sintoma();
			s.setNombre(ss[i]);
			this.sintomas.add(s);
		}
		
		boolean ok = EnfermedadBeanController.crearEnfermedadInfecciosa(this.nombreEnfermedad, this.nombreTipoEnfermedad, this.nombreAgente, this.sintomas);
		if (ok)
			this.mensaje = "Se agrego la solicitud de alta de la enfermedad " + this.nombreEnfermedad + ". Un administrador debe aprobarla para que quede dada de alta."; 
	}
}
