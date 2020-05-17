package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity(name = "RecursoEnfermedad")
@Table(name = "recurso_enfermedad")
//@IdClass(IdRecursoEnfermedad.class)
public class RecursoEnfermedad implements Serializable {

	private static final long serialVersionUID = 812406670467008986L;

	@EmbeddedId
	private IdRecursoEnfermedad id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idRecurso")
	@JoinColumn(name = "id_recurso")
	private Recurso recurso;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idEnfermedad")
	@JoinColumn(name = "id_enfermedad")
	private Enfermedad enfermedad;

	@Column(name = "\"recurso_trata\"")
	private boolean recursoTrata;

	@Column(name = "\"recurso_previene\"")
	private boolean recursoPreviene;

	public RecursoEnfermedad() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public RecursoEnfermedad(Recurso recurso, Enfermedad enfermedad) {
		super();
		this.enfermedad = enfermedad;
		this.recurso = recurso;
		this.id = new IdRecursoEnfermedad(recurso.getId(), enfermedad.getId());
	}



	public IdRecursoEnfermedad getId() {
		return id;
	}

	public void setId(IdRecursoEnfermedad id) {
		this.id = id;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public boolean isRecursoTrata() {
		return recursoTrata;
	}

	public void setRecursoTrata(boolean recursoTrata) {
		this.recursoTrata = recursoTrata;
	}

	public boolean isRecursoPreviene() {
		return recursoPreviene;
	}

	public void setRecursoPreviene(boolean recursoPreviene) {
		this.recursoPreviene = recursoPreviene;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enfermedad == null) ? 0 : enfermedad.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((recurso == null) ? 0 : recurso.hashCode());
		result = prime * result + (recursoPreviene ? 1231 : 1237);
		result = prime * result + (recursoTrata ? 1231 : 1237);
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
		RecursoEnfermedad other = (RecursoEnfermedad) obj;
		if (enfermedad == null) {
			if (other.enfermedad != null)
				return false;
		} else if (!enfermedad.equals(other.enfermedad))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (recurso == null) {
			if (other.recurso != null)
				return false;
		} else if (!recurso.equals(other.recurso))
			return false;
		if (recursoPreviene != other.recursoPreviene)
			return false;
		if (recursoTrata != other.recursoTrata)
			return false;
		return true;
	}

}