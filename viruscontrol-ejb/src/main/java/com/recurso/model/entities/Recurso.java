package com.recurso.model.entities;

import java.io.Serializable;

public class Recurso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigo;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	private TipoRecurso tipoRecurso;
	
	private String marca;
	
	public TipoRecurso getTipoRecurso() {
		return tipoRecurso;
	}
	public void setTipoRecurso(TipoRecurso tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Recurso() {
		super();
	}
	
	

}
