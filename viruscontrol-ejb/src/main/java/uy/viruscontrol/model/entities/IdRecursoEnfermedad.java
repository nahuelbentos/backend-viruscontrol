package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.Column;

public class IdRecursoEnfermedad implements Serializable{

	 
	private static final long serialVersionUID = -342666877353966803L;

		
	    private int idEnfermedad;

		
		private int idRecurso;
	 
	 

	public IdRecursoEnfermedad() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public IdRecursoEnfermedad(int idEnfermedad, int idRecurso) {
		super();
		this.idEnfermedad = idEnfermedad;
		this.idRecurso = idRecurso;
	}



	public int getIdEnfermedad() {
		return idEnfermedad;
	}

	public void setIdEnfermedad(int idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public int getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(int idRecurso) {
		this.idRecurso = idRecurso;
	}
 
	 
}
