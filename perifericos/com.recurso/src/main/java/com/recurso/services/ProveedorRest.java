package com.recurso.services;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import com.recurso.model.DummyProveedor;
import com.recurso.model.HandlerModel;
import com.recurso.model.entities.ProveedorRecurso;
import com.recurso.model.entities.TipoProveedor;

@ApplicationScoped
public class ProveedorRest implements IProveedorRest{

	@EJB HandlerModel handler;
	
	@Override
	public List<DummyProveedor> getProveedores() {
		return handler.getProveedores();
	}

	@Override
	public List<ProveedorRecurso> getRecursosDisponibles(String codigoProveedor) {
		// TODO Auto-generated method stub
		return null;
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
