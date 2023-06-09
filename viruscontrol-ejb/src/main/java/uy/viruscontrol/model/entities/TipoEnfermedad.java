package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_enfermedad")
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		TipoEnfermedad other = (TipoEnfermedad) obj;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	
	
}
