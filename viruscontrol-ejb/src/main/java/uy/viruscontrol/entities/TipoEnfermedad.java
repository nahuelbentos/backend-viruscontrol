package uy.viruscontrol.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoenfermedad")
public class TipoEnfermedad implements Serializable{
	private static final long serialVersionUID = 4391883901530988682L;
	
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


	@Override
	public String toString() {
		return "TipoEnfermedad [id=" + id + ", nombre=" + nombre + "]";
	}

	
	
}
