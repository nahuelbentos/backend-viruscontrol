package com.recurso.services;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import com.recurso.model.HandlerModel;
import com.recurso.model.entities.DummyProveedor;
import com.recurso.model.entities.DummyRecursoDisponible;
import com.recurso.model.entities.TipoProveedor;

@ApplicationScoped
public class ProveedorRest implements IProveedorRest{

	@EJB HandlerModel handler;
	
	@Override
	public List<DummyProveedor> getProveedores() {
		return handler.getProveedores();
	}

	@Override
	public List<DummyRecursoDisponible> getRecursosDisponibles(String codigoProveedor) {
		return handler.getRecursosDisponiblesPorProveedor(codigoProveedor);
	}
	
	@Override
	public List<DummyRecursoDisponible> getRecursosDisponiblesPorCiudadBarrio(String ciudad, String barrio) {
		return handler.getRecursosDisponiblesPorCiudadBarrio(ciudad, barrio);
	}

	@Override
	public List<DummyProveedor> getProveedoresDeTipo(String codigoTipo) {
		return handler.getProveedoresDeTipo(codigoTipo);
	}

	@Override
	public List<TipoProveedor> getTiposProveedor() {
		return handler.getTiposProveedores();
	}

	@Override
	public boolean adquirirRecursoDisponible(String codigoProveedor, String codigoRecurso, int cantidad) {
		return handler.adquirirRecursoDisponible(codigoProveedor, codigoRecurso, cantidad);
			
	}
	
	

}
