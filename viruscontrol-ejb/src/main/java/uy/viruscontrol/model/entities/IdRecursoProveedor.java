package uy.viruscontrol.model.entities;

import java.io.Serializable;

public class IdRecursoProveedor implements Serializable {
	private static final long serialVersionUID = 1731036511727260425L;
	
	private Recurso recurso;
	private ProveedorRecursos proveedor;
	
	public IdRecursoProveedor() {
		super();	
	}

	public IdRecursoProveedor(Recurso recurso, ProveedorRecursos proveedor) {
		super();
		this.recurso = recurso;
		this.proveedor = proveedor;
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
