package uy.viruscontrol.utils;

import java.io.Serializable;

public class DtVisita implements Serializable {
	private static final long serialVersionUID = -111031098057568711L;
	
	private int idVisita;
	private int medicoId;
	private String medicoNomApe;
	private String fecha;
	
	public DtVisita() {
		super();
	}

	public DtVisita(int idVisita, int medicoId, String medicoNomApe, String fecha) {
		super();
		this.idVisita = idVisita;
		this.medicoId = medicoId;
		this.medicoNomApe = medicoNomApe;
		this.fecha = fecha;
	}

	public int getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(int medicoId) {
		this.medicoId = medicoId;
	}

	public String getMedicoNomApe() {
		return medicoNomApe;
	}

	public void setMedicoNomApe(String medicoNomApe) {
		this.medicoNomApe = medicoNomApe;
	}

	public int getIdVisita() {
		return idVisita;
	}

	public void setIdVisita(int idVisita) {
		this.idVisita = idVisita;
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fechaLinda) {
		this.fecha = fechaLinda;
	}

}
