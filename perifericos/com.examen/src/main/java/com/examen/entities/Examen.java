package com.examen.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.examen.utils.DtExamen;

@Entity
@Table(name = "examen")
public class Examen implements Serializable {
	private static final long serialVersionUID = -4187642113394060508L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Enfermedad enfermedad;
	@Column
	private String nombre;

	// segun el modelo de dominio y DCD, varios casos pueden ir asociados a un examen, por lo que el examen no puede tener un estado, dado que no está asociado a un ciudadano
//	@Enumerated
//	@Column
//	private EstadoExamen estado;
	
//	public EstadoExamen getEstado() {
//		return estado;
//	}
//	public void setEstado(EstadoExamen estado) {
//		this.estado = estado;
//	}
	
	public Examen() {
		super();
	}
	public Examen(Enfermedad enfermedad) {
		super();
		this.enfermedad = enfermedad;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public DtExamen getDt() {
		return new DtExamen(this.id, this.nombre, this.enfermedad.getId(), this.enfermedad.getNombre());
	}
}
