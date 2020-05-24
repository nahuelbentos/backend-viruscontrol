package uy.viruscontrol.utils;

import java.io.Serializable;

public class DtCaso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9029309223542629483L;
	//return medicoBean.nuevoCaso(idDepartamento, idExamen, idEnfermedad, idPaciente, idMedico);
	private int idDepartamento;
	private int idExamen;
	private int idEnfermedad;
	private int idPaciente;
	private int idMedico;
	
	public DtCaso() {
		
	}
	
	public DtCaso(int idDepartamento, int idExamen, int idEnfermedad, int idPaciente, int idMedico) {
		super();
		this.idDepartamento = idDepartamento;
		this.idExamen = idExamen;
		this.idEnfermedad = idEnfermedad;
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
	}


	public int getIdDepartamento() {
		return idDepartamento;
	}


	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}


	public int getIdExamen() {
		return idExamen;
	}


	public void setIdExamen(int idExamen) {
		this.idExamen = idExamen;
	}


	public int getIdEnfermedad() {
		return idEnfermedad;
	}


	public void setIdEnfermedad(int idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}


	public int getIdPaciente() {
		return idPaciente;
	}


	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}


	public int getIdMedico() {
		return idMedico;
	}


	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
	

}
