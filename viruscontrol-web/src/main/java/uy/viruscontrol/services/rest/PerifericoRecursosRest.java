package uy.viruscontrol.services.rest;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import uy.viruscontrol.bussines.interfaces.ProveedorBeanLocal;

@ApplicationScoped
public class PerifericoRecursosRest implements IPerifericoRecursosRest {

	@EJB private ProveedorBeanLocal proveedorEjb;
	
	@Override
	public Response deleteRecurso(String codigoPrvPeriferico, String codigoRecPeriferico) {
		try {
			return Response.status(Status.OK).entity(proveedorEjb.eliminarRecursoProveedor(codigoPrvPeriferico, codigoRecPeriferico)).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public Response addRecurso(String codigoPrvPeriferico, String codigoRecPeriferico) {
		try {
			return Response.status(Status.OK).entity(proveedorEjb.altaRecursoProveedor(codigoPrvPeriferico, codigoRecPeriferico)).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
