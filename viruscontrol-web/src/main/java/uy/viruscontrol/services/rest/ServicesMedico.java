package uy.viruscontrol.services.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.viruscontrol.bussines.interfaces.MedicoBeanLocal;
import uy.viruscontrol.model.entities.Ciudadano;


@ApplicationScoped
@Path("/medico")
public class ServicesMedico {
	@EJB private MedicoBeanLocal medicoBean;
	
	@GET
	@Path("/ciudadanos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ciudadano> obtenerCiudadanos() {
		return medicoBean.mostrarCiudadanos();
	}
	
	
	
	
}
