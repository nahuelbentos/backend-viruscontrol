package uy.viruscontrol.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uy.viruscontrol.model.entities.ProveedorRecursos;

public class DtRecursosProveedor implements Serializable {
	private static final long serialVersionUID = -761047223229518962L;

	private ProveedorRecursos proveedor;
	private List<DtRecurso> recursos = new ArrayList<DtRecurso>();

	public DtRecursosProveedor() {
		super();
	}

	public List<DtRecurso> getRecurso() {
		return recursos;
	}
	public void setRecurso(List<DtRecurso> recursos) {
		this.recursos = recursos;
	}
	public void addRecurso(DtRecurso recurso) {
		this.recursos.add(recurso);
	}
	public ProveedorRecursos getProveedor() {
		return proveedor;
	}
	public void setProveedor(ProveedorRecursos proveedor) {
		this.proveedor = proveedor;
	}

}
