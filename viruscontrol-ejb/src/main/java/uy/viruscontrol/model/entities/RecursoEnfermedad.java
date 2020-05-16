	package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "RecursoEnfermedad")
@Table(name = "recurso_enfermedad")
@IdClass(IdRecursoEnfermedad.class)
public class RecursoEnfermedad implements Serializable{

	private static final long serialVersionUID = 812406670467008986L;
	
	@Id
	@Column(name = "id_enfermedad")
	private int idEnfermedad;
	
	@Id
	@Column(name = "id_recurso")
	private int idRecurso;
	
	@Column(name="\"recurso_trata\"")
	private boolean recursoTrata;
	
	@Column(name="\"recurso_previene\"")
	private boolean recursoPreviene;
	
	@ManyToOne
	@JoinColumn(name = "id_enfermedad", referencedColumnName = "id",insertable = false, updatable = false)
	private Enfermedad enfermedad;
	
	@ManyToOne
	@JoinColumn(name = "id_recurso", referencedColumnName = "id",insertable = false, updatable = false)
	private Recurso recurso;

	public RecursoEnfermedad(int idEnfermedad, int idRecurso, boolean recursoTrata, boolean recursoPreviene,
			Enfermedad enfermedad, Recurso recurso) {
		super();
		this.idEnfermedad = idEnfermedad;
		this.idRecurso = idRecurso;
		this.recursoTrata = recursoTrata;
		this.recursoPreviene = recursoPreviene;
		this.enfermedad = enfermedad;
		this.recurso = recurso;
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

}
