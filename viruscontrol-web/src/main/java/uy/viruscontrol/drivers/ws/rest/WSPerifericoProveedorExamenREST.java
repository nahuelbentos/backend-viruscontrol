package uy.viruscontrol.drivers.ws.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.viruscontrol.drivers.PerifericoProveedorExamenLocal;
import uy.viruscontrol.model.entities.EstadoExamen;
import uy.viruscontrol.model.entities.Examen;
import uy.viruscontrol.model.entities.ProveedorExamen;

@ApplicationScoped
@Path("/perifprovex")
public class WSPerifericoProveedorExamenREST {
	@EJB private PerifericoProveedorExamenLocal provEx;
	
	@GET
	@Path("/proveedor/{idEnfermedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProveedorExamen obtenerProveedor(@PathParam("idEnfermedad") int idEnfermedad) {
		return provEx.find(idEnfermedad);
	}
	
	@GET
	@Path("/examenes/{idEnfermedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Examen> obtenerExamenesParaUnaEnfermedad(@PathParam("idEnfermedad") int idEnfermedad){
		return provEx.listaExamenesParaEnfermedad(idEnfermedad);
	}
	
	@POST
	@Path("/examen")
	@Produces(MediaType.APPLICATION_JSON)
	public Examen altaDeExamen(@FormParam("idPaciente") int idPaciente, 
									@FormParam("idExamen") int idExamen,
									@FormParam("idMedico") int idMedico) {
		return provEx.solicitarAltaExamen(idPaciente, idExamen, idMedico);
	}
	
	@GET
	@Path("/resultados/{idCaso}")
	@Produces(MediaType.APPLICATION_JSON)
	public EstadoExamen obtenerResultadoExamen(@PathParam("idCaso") int idCaso) {
		return provEx.resultadoExamen(idCaso);
	}
}
