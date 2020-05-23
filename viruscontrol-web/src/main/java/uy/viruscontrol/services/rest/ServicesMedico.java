package uy.viruscontrol.services.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import uy.viruscontrol.bussines.interfaces.MedicoBeanLocal;
import uy.viruscontrol.utils.VisitaPendiente;

@ApplicationScoped
public class ServicesMedico implements IServicesMedico {

	@EJB private MedicoBeanLocal medicoBean; 
	
	@Override
	public List<VisitaPendiente> getVisitasPendientes(String username) {
		return medicoBean.getVisitaPendiente(username);
	}

	@Override
	public Response ping() {
		return Response.status(Status.OK).build();
	}

}
