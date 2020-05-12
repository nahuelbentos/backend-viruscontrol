package uy.viruscontrol.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoenfermedad")
public class TipoEnfermedad {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nombre;
	
	
	public TipoEnfermedad() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TipoEnfermedad(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}


	public int getid() {
		return id;
	}


	public void setid(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
