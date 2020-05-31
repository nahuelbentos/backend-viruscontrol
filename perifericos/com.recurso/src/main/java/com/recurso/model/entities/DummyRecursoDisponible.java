package com.recurso.model.entities;

public class DummyRecursoDisponible {
	private Recurso recurso;
	private double precio;
	private int cantidadDisponible;
	public Recurso getRecurso() {
		return recurso;
	}
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCantidadDisponible() {
		return cantidadDisponible;
	}
	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}
	public DummyRecursoDisponible() {
		super();
	}
}
