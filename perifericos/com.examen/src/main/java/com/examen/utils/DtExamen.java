package com.examen.utils;

import java.io.Serializable;

public class DtExamen implements Serializable {
	private static final long serialVersionUID = 4307061725748956684L;
	
	private int id;
	private String nombre;
	private int idEnfermedad;
	private String nombreEnfermedad;
	
	public DtExamen() {
		super();
	}

	public DtExamen(int id, String nombre, int idEnfermedad, String nombreEnfermedad) {
		super();
		this.id = id;
		this.setNombre(nombre);
		this.idEnfermedad = idEnfermedad;
		this.nombreEnfermedad = nombreEnfermedad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEnfermedad() {
		return idEnfermedad;
	}

	public void setIdEnfermedad(int idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public String getNombreEnfermedad() {
		return nombreEnfermedad;
	}

	public void setNombreEnfermedad(String nombreEnfermedad) {
		this.nombreEnfermedad = nombreEnfermedad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
