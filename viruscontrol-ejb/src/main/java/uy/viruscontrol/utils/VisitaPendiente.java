package uy.viruscontrol.utils;

import java.io.Serializable;

public class VisitaPendiente implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String apellido;
	private DireccionDT direccion;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public DireccionDT getDireccion() {
		return direccion;
	}
	public void setDireccion(DireccionDT direccion) {
		this.direccion = direccion;
	}
	public VisitaPendiente() {
		super();
	}
	
	
	
}
