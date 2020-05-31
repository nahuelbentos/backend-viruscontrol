package uy.viruscontrol.utils;

import java.io.Serializable;
import java.util.List;

import uy.viruscontrol.model.entities.Sintoma;

public class VisitaPendiente implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String nombre;
	private String apellido;
	private String direccion;
	private String fecha;
	private List<Sintoma> sintomas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public List<Sintoma> getSintomas() {
		return sintomas;
	}
	public void setSintomas(List<Sintoma> sintomas) {
		this.sintomas = sintomas;
	}
	public VisitaPendiente() {
		super();
	}
}
