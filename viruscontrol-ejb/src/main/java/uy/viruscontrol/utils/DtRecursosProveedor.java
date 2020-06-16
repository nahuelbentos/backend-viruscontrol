package uy.viruscontrol.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uy.viruscontrol.model.entities.ProveedorRecursos;
import uy.viruscontrol.model.entities.Recurso;

public class DtRecursosProveedor implements Serializable {
	private static final long serialVersionUID = -761047223229518962L;

	private ProveedorRecursos proveedor;
	private List<Recurso> recursos = new ArrayList<Recurso>();

	public DtRecursosProveedor() {
		super();
	}

	public List<Recurso> getRecurso() {
		return recursos;
	}
	public void setRecurso(List<Recurso> recursos) {
		this.recursos = recursos;
	}
	public void addRecurso(Recurso recurso) {
		this.recursos.add(recurso);
	}
	public ProveedorRecursos getProveedor() {
		return proveedor;
	}
	public void setProveedor(ProveedorRecursos proveedor) {
		this.proveedor = proveedor;
	}

}
