package com.recurso.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Proveedor implements Serializable{
	private static final long serialVersionUID = 2020480372753741772L;
	
	private String nombre;
	private String codigo;
	private String direccion;
	private String barrio;
	private String horarioAtencion;
	
	private List<ProveedorRecurso> recursosDisponibles;
	private TipoProveedor tipoProveedor;
	private String ciudad;
	
	public Proveedor() {
		super();
		this.recursosDisponibles = new ArrayList<ProveedorRecurso>();
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getHorarioAtencion() {
		return horarioAtencion;
	}
	public void setHorarioAtencion(String horarioAtencion) {
		this.horarioAtencion = horarioAtencion;
	}
	public List<ProveedorRecurso> getRecursosDisponibles() {
		return recursosDisponibles;
	}
	public void setRecursosDisponibles(List<ProveedorRecurso> recursosDisponibles) {
		this.recursosDisponibles = recursosDisponibles;
	}
	public TipoProveedor getTipoProveedor() {
		return tipoProveedor;
	}
	public void setTipoProveedor(TipoProveedor tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}
	public void addRecursoDisponible(ProveedorRecurso pr) {
		this.recursosDisponibles.add(pr);
	}
	public ProveedorRecurso findProveedorRecurso(String codigoRecurso) throws Exception {
		for (ProveedorRecurso proveedorRecurso : this.recursosDisponibles) 
			if (proveedorRecurso.getRecurso().getCodigo().equals(codigoRecurso))
				return proveedorRecurso;
		
		throw new Exception("No existe el recurso para el proveedor " + this.codigo);
	}
	
	public void updateRecursoDisponible(ProveedorRecurso pr) {
		int index = -1;
		for (int i = 0; i < this.recursosDisponibles.size(); i++) {
			if (this.recursosDisponibles.get(i).getRecurso().getCodigo().equals(pr.getRecurso().getCodigo()))
				index = i;
			
			if (index > -1)
				break;
		}
		
		if (index > -1)
			this.recursosDisponibles.remove(index);
		
		this.recursosDisponibles.add(pr);
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	

}
