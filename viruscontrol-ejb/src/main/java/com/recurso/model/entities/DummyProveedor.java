package com.recurso.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DummyProveedor implements Serializable{
	private static final long serialVersionUID = -4814859568972226804L;
	
	private String nombre;
	private String codigo;
	private String direccion;
	private String barrio;
	private String horarioAtencion;
	private String ciudad;
	private List<DummyRecursoDisponible> recursosDisponibles = new ArrayList<DummyRecursoDisponible>();
	
	public DummyProveedor() {
		super();
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
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public List<DummyRecursoDisponible> getRecursosDisponibles() {
		return recursosDisponibles;
	}
	public void setRecursosDisponibles(List<DummyRecursoDisponible> recursosDisponibles) {
		this.recursosDisponibles = recursosDisponibles;
	}
	public void addRecursoDisponible(DummyRecursoDisponible recursoDisponible) {
		this.recursosDisponibles.add(recursoDisponible);
	}
}
