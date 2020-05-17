package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdRecursoEnfermedad implements Serializable{

	 
	private static final long serialVersionUID = -342666877353966803L;

		@Column(name = "enfermedad_id")
	    private int idEnfermedad;

		@Column(name = "recurso_id")
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEnfermedad;
		result = prime * result + idRecurso;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdRecursoEnfermedad other = (IdRecursoEnfermedad) obj;
		if (idEnfermedad != other.idEnfermedad)
			return false;
		if (idRecurso != other.idRecurso)
			return false;
		return true;
	}
	
	
 
	 
}