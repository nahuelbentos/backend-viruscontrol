package uy.viruscontrol.entities;

import java.io.Serializable;

public class IdRecursoProveedor implements Serializable {
	private static final long serialVersionUID = 1731036511727260425L;
	
	private Recurso recurso;
	private Proveedor proveedor;
	
	public IdRecursoProveedor() {
		super();	
	}

	public IdRecursoProveedor(Recurso recurso, Proveedor proveedor) {
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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}
