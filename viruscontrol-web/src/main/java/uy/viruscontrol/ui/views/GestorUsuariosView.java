package uy.viruscontrol.ui.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	private Ciudadano ciudadanoSeleccionado;
	private Medico medicoSeleccionado;
	private Gerente gerenteSeleccionado;
	private Administrador adminSeleccionado;
	private String nombre;
	private String apellido;
	private String correo;
	private String direccion;
	private String userName;
	private String nacionalidad;
	private String password;
	private Date fecha;
	
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

public void editarCiudadano() {
	if(ciudadanoSeleccionado!=null) {
		System.out.println("usuario seleccionado "+ciudadanoSeleccionado.getNombre());
	
	
	if(nombre!=null) {
		System.out.println("nombre que escribio es "+nombre);
	}
	if(fecha!=null) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		System.out.println(calendar.getTime());
		UsuarioBeanController.editarCiudadano(ciudadanoSeleccionado.getIdUsuario(), nombre, apellido, correo, direccion, nacionalidad, userName,calendar);
		
	}else {
		UsuarioBeanController.editarCiudadano(ciudadanoSeleccionado.getIdUsuario(), nombre, apellido, correo, direccion, nacionalidad, userName,null);
	}
	
	}
	
	
}

public void editarMedico() {
	if(medicoSeleccionado!=null) {
		
	
	if(fecha!=null) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		System.out.println(calendar.getTime());
		UsuarioBeanController.editarMedico(medicoSeleccionado.getIdUsuario(), nombre, apellido, correo, direccion, nacionalidad, userName,calendar);
		
	}else {
		UsuarioBeanController.editarMedico(medicoSeleccionado.getIdUsuario(), nombre, apellido, correo, direccion, nacionalidad, userName,null);
	}
	}
}


public void editarGerente() {
	if(gerenteSeleccionado!=null) {
		
	
	if(fecha!=null) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		System.out.println(calendar.getTime());
		UsuarioBeanController.editarGerente(gerenteSeleccionado.getIdUsuario(), nombre, apellido, correo, direccion, nacionalidad, userName,calendar,password);
		
	}else {
		UsuarioBeanController.editarGerente(gerenteSeleccionado.getIdUsuario(), nombre, apellido, correo, direccion, nacionalidad, userName,null,password);
	}
	}
}

public void editarAdmin() {
	if(adminSeleccionado!=null) {
		
	
	if(fecha!=null) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		System.out.println(calendar.getTime());
		UsuarioBeanController.editarAdmin(adminSeleccionado.getIdUsuario(), nombre, apellido, correo, direccion, nacionalidad, userName,calendar,password);
		
	}else {
		UsuarioBeanController.editarAdmin(adminSeleccionado.getIdUsuario(), nombre, apellido, correo, direccion, nacionalidad, userName,null,password);
	}
	}
}



public Administrador getAdminSeleccionado() {
	return adminSeleccionado;
}

public void setAdminSeleccionado(Administrador adminSeleccionado) {
	this.adminSeleccionado = adminSeleccionado;
}

public Gerente getGerenteSeleccionado() {
	return gerenteSeleccionado;
}

public void setGerenteSeleccionado(Gerente gerenteSeleccionado) {
	this.gerenteSeleccionado = gerenteSeleccionado;
}

public Medico getMedicoSeleccionado() {
	return medicoSeleccionado;
}

public void setMedicoSeleccionado(Medico medicoSeleccionado) {
	this.medicoSeleccionado = medicoSeleccionado;
}

public Date getFecha() {
	return fecha;
}

public void setFecha(Date fecha) {
	this.fecha = fecha;
}

public String getNacionalidad() {
	return nacionalidad;
}

public void setNacionalidad(String nacionalidad) {
	this.nacionalidad = nacionalidad;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getApellido() {
	return apellido;
}

public void setApellido(String apellido) {
	this.apellido = apellido;
}

public String getCorreo() {
	return correo;
}

public void setCorreo(String correo) {
	this.correo = correo;
}

public String getDireccion() {
	return direccion;
}

public void setDireccion(String direccion) {
	this.direccion = direccion;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
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

public Ciudadano getCiudadanoSeleccionado() {
	return ciudadanoSeleccionado;
}

public void setCiudadanoSeleccionado(Ciudadano ciudadanoSeleccionado) {
	this.ciudadanoSeleccionado = ciudadanoSeleccionado;
}

	
	

}
