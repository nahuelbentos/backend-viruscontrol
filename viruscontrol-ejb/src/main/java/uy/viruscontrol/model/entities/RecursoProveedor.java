package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(IdRecursoProveedor.class)
@Table(name = "recurso_proveedor")
public class RecursoProveedor implements Serializable {
	private static final long serialVersionUID = -2614777330637747425L;
	
	@Id
	@Column(name = "id_recurso")
	private Recurso recurso;
	
	@Id
	@Column(name = "id_proveedor")
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

}
