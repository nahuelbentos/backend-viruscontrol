package uy.gub.rucaf.entities;

import java.io.Serializable;

public class PrestadoraSalud implements Serializable {
	private static final long serialVersionUID = -1223087316446990000L;
	
	private int id;
	private String nombre;
	
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
	
	@Override
	public String toString() {
		return this.getId()+";"+this.getNombre()+";";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		PrestadoraSalud other = (PrestadoraSalud) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
