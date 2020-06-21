package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(IdRecursoProveedor.class)
@Table(name = "recurso_proveedor")
public class RecursoProveedor implements Serializable {
	private static final long serialVersionUID = -2614777330637747425L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_recurso")
	private Recurso recurso;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_proveedor")
	private ProveedorRecursos proveedor;
	
	@Column
	private int cantidad;

	public RecursoProveedor() {
		super();
	}

	public RecursoProveedor(Recurso recurso, ProveedorRecursos proveedor, int cantidad) {
		super();
		this.recurso = recurso;
		this.proveedor = proveedor;
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public ProveedorRecursos getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorRecursos proveedor) {
		this.proveedor = proveedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + ((proveedor == null) ? 0 : proveedor.hashCode());
		result = prime * result + ((recurso == null) ? 0 : recurso.hashCode());
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
		RecursoProveedor other = (RecursoProveedor) obj;
		if (cantidad != other.cantidad)
			return false;
		if (proveedor == null) {
			if (other.proveedor != null)
				return false;
		} else if (!proveedor.equals(other.proveedor))
			return false;
		if (recurso == null) {
			if (other.recurso != null)
				return false;
		} else if (!recurso.equals(other.recurso))
			return false;
		return true;
	}

	
}
