package uy.viruscontrol.ui.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import uy.viruscontrol.controllers.UsuarioBeanController;

import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.entities.Medico;



@Named("GestorUsuariosView")
@RequestScoped
public class GestorUsuariosView implements Serializable {


	private static final long serialVersionUID = -9187425136651928924L;
	
	private List<Ciudadano> ciudadanos;
	private List<Medico> medicos;
	private List<Gerente> gerentes;
	private List<Administrador> administradores;
	
	
	public List<Gerente> mostrarGerentes(){
	
		
		return UsuarioBeanController.mostrarGerentes();

	}
	
	public List<Administrador> mostrarAdministradores(){
		return UsuarioBeanController.mostrarAdministradores();
	}
	
	/**
	 * @return
	 */
	public List<Ciudadano> mostrarCiudadanos(){
		
		/*
		 * List<String> listaCiudadanos= new ArrayList<String>(); for(Ciudadano
		 * c:UsuarioBeanController.mostrarCiudadanos()) {
		 * listaCiudadanos.add(c.getNombre()); }
		 */

		return UsuarioBeanController.mostrarCiudadanos();
	}
public List<Medico> mostrarMedicos(){

		return UsuarioBeanController.mostrarMedicos();
	}
public List<Ciudadano> getCiudadanos() {
	return ciudadanos;
}
public void setCiudadanos(List<Ciudadano> ciudadanos) {
	this.ciudadanos = ciudadanos;
}
public List<Medico> getMedicos() {
	return medicos;
}
public void setMedicos(List<Medico> medicos) {
	this.medicos = medicos;
}
public List<Gerente> getGerentes() {
	return gerentes;
}
public void setGerentes(List<Gerente> gerentes) {
	this.gerentes = gerentes;
}
public List<Administrador> getAdministradores() {
	return administradores;
}
public void setAdministradores(List<Administrador> administradores) {
	this.administradores = administradores;
}

	
	

}
