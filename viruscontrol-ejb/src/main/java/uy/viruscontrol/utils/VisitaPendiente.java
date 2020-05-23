package uy.viruscontrol.utils;

import java.io.Serializable;
import java.util.Calendar;

public class VisitaPendiente implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String apellido;
	private String direccion;
	private String fecha;
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public VisitaPendiente() {
		super();
	}
	
	
	
}
