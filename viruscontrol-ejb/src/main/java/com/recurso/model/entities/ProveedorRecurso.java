package com.recurso.model.entities;

import java.io.Serializable;

public class ProveedorRecurso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Recurso recurso;
	private double precio;
	private int cantidadDisponible;
	private Proveedor proveedor;
	
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
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public ProveedorRecurso() {
		super();
	}

}
