package uy.viruscontrol.utils;

import java.io.Serializable;

public class DtSuscripcion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9173713752775926331L;
	
	private String barrio;
	private int idRecurso;
	
	public DtSuscripcion() {
		super();
	}

	public DtSuscripcion(String barrio, int idRecurso) {
		super();
		this.barrio = barrio;
		this.idRecurso = idRecurso;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public int getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(int idRecurso) {
		this.idRecurso = idRecurso;
	}
	
	
	
	
	
}
