package uy.viruscontrol.utils;

import java.io.Serializable;

public class DtProveedorExamen implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4151785662308532258L;
	private int id;
	private String nombre;
	private String direccion;
	private String barrio;
	private String rangoHorario;
	
	public DtProveedorExamen(int id, String nombre, String direccion, String barrio, String rangoHorario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.barrio = barrio;
		this.rangoHorario = rangoHorario;
	}
	public DtProveedorExamen() {
		super();
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getRangoHorario() {
		return rangoHorario;
	}
	public void setRangoHorario(String rangoHorario) {
		this.rangoHorario = rangoHorario;
	}
	
	
	
}
