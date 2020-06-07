package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "proveedor")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="proveedor_tipo")
public abstract class Proveedor implements Serializable {
	private static final long serialVersionUID = 8494382571373859359L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private String nombre;
	
	@Column
	private String direccion;
	
	@Column
	private String barrio;
	
	@Column
	private String ciudad;

	@Column
	private String rangoHorario;
	
	private boolean deleted;
	
	public Proveedor() {
		
	}

	public Proveedor(int id, String nombre,String direccion,String barrio,String rangoHorario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion= direccion;
	}
	
	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	
	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getRangoHorario() {
		return rangoHorario;
	}

	public void setRangoHorario(String rangoHorario) {
		this.rangoHorario = rangoHorario;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
		Proveedor other = (Proveedor) obj;
		if (id != other.id)
			return false;
		return true;
		
		
	}

	@Override
	public String toString() {
		return id + " - " + nombre;
	}

	
	
	

}
