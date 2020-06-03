package uy.viruscontrol.utils;

import java.io.Serializable;

public class DtEnfermedad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7671130396988244795L;
	private int id;
	private String nombre;
	
	
	public DtEnfermedad() {
		super();
		
	}
	
	public DtEnfermedad(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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
	
	
}
