package uy.viruscontrol.utils;

import java.io.Serializable;

import com.recurso.model.entities.Recurso;

public class DtRecursoDisponibleProveedor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6261362296802455775L;
	private Recurso recurso;
	private double precio;
	private int cantidadDisponible;
	
	
	
	
	public DtRecursoDisponibleProveedor() {
		super();
		// TODO Auto-generated constructor stub
	}




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
	
	
	
}
