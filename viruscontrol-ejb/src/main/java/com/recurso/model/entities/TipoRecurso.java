package com.recurso.model.entities;

import java.io.Serializable;

public class TipoRecurso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigo;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	private String nombre;
	private double precioReferencia;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecioReferencia() {
		return precioReferencia;
	}
	public void setPrecioReferencia(double precioReferencia) {
		this.precioReferencia = precioReferencia;
	}
	public TipoRecurso() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
