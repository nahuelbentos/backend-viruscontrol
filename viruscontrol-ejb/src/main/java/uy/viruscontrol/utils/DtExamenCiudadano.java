package uy.viruscontrol.utils;

import java.io.Serializable;

import javax.persistence.Enumerated;

import uy.viruscontrol.bussines.enumerated.TipoCaso;

public class DtExamenCiudadano implements Serializable{
	
	private static final long serialVersionUID = -8108085461846147469L;
	
	private int idExamen;
	private String nombreExamen;
	@Enumerated
	private TipoCaso resultadoExamen;
	
	public DtExamenCiudadano() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DtExamenCiudadano(int idExamen, String nombreExamen, TipoCaso resultadoExamen) {
		super();
		this.idExamen = idExamen;
		this.nombreExamen = nombreExamen;
		this.resultadoExamen = resultadoExamen;
	}

	public int getIdExamen() {
		return idExamen;
	}

	public void setIdExamen(int idExamen) {
		this.idExamen = idExamen;
	}

	public String getNombreExamen() {
		return nombreExamen;
	}

	public void setNombreExamen(String nombreExamen) {
		this.nombreExamen = nombreExamen;
	}

	public TipoCaso getResultadoExamen() {
		return resultadoExamen;
	}

	public void setResultadoExamen(TipoCaso resultadoExamen) {
		this.resultadoExamen = resultadoExamen;
	}
	
	

}
