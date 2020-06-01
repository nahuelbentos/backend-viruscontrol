package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prestadora_salud")
public class PrestadoraSalud implements Serializable {
	private static final long serialVersionUID = -1223087316446990000L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private String nombre;
	@Column(name="\"id_prestadora_rucaf\"")
	private int idPrestadoraRucaf;
	
	private boolean deleted;
	
	public PrestadoraSalud() {
		super();
	}

	public PrestadoraSalud(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getIdPrestadoraRucaf() {
		return idPrestadoraRucaf;
	}

	public void setIdPrestadoraRucaf(int idPrestadoraRucaf) {
		this.idPrestadoraRucaf = idPrestadoraRucaf;
	}

	@Override
	public String toString() {
		return id + " - " + nombre;
	}

	
	
	
}
