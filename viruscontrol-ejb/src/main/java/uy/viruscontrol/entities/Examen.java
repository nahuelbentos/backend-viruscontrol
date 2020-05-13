package uy.viruscontrol.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "examen")
public class Examen {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Enfermedad enfermedad;
	@Column
	private EstadoExamen estado;
	
	
	
	public EstadoExamen getEstado() {
		return estado;
	}
	public void setEstado(EstadoExamen estado) {
		this.estado = estado;
	}
	public Examen() {
		super();
	}
	public Examen(Enfermedad enfermedad) {
		super();
		this.enfermedad = enfermedad;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Enfermedad getEnfermedad() {
		return enfermedad;
	}
	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}
}
