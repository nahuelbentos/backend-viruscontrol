package uy.viruscontrol.utils;

import java.io.Serializable;

public class DtSuscripcion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9173713752775926331L;
	
	private String barrio;
	private String recurso;
	
	public DtSuscripcion() {
		super();
	}
	
	public DtSuscripcion(int ciudadanoId, String barrio, String recurso) {
		super();
		this.barrio = barrio;
		this.recurso = recurso;
	}
	
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getRecurso() {
		return recurso;
	}
	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}
	
	
	
	
}
