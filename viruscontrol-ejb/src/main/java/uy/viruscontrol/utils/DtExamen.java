package uy.viruscontrol.utils;

import java.io.Serializable;

public class DtExamen implements Serializable {
	private static final long serialVersionUID = 4307061725748956684L;
	
	private int id;
	private int idEnfermedad;
	private String nombreEnfermedad;
	
	public DtExamen() {
		super();
	}

	public DtExamen(int id, int idEnfermedad, String nombreEnfermedad) {
		super();
		this.id = id;
		this.idEnfermedad = idEnfermedad;
		this.nombreEnfermedad = nombreEnfermedad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEnfermedad() {
		return idEnfermedad;
	}

	public void setIdEnfermedad(int idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public String getNombreEnfermedad() {
		return nombreEnfermedad;
	}

	public void setNombreEnfermedad(String nombreEnfermedad) {
		this.nombreEnfermedad = nombreEnfermedad;
	}
	

}
