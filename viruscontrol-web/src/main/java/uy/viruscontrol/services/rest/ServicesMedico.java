package uy.viruscontrol.services.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import uy.viruscontrol.bussines.interfaces.MedicoBeanLocal;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Departamento;
import uy.viruscontrol.model.entities.Examen;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.utils.DtCaso;
import uy.viruscontrol.utils.DtEnfermedad;
import uy.viruscontrol.utils.DtExamen;
import uy.viruscontrol.utils.DtProveedorExamen;
import uy.viruscontrol.utils.VisitaPendiente;

@ApplicationScoped
@Path("/medico")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicesMedico {

@EJB private MedicoBeanLocal medicoBean;
	

	//http://localhost:8080/viruscontrol-web/rest/medico/ciudadanos
	@GET
	@Path("/ciudadanos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ciudadano> obtenerCiudadanos() {
		return medicoBean.mostrarCiudadanos();
	}
	
	
	//http://localhost:8080/viruscontrol-web/rest/medico/examenesenfermedad/{idEnfermedad}
/*	@GET
	@Path("/examenesenfermedad/{idEnfermedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DtExamen> obtenerExamenesDeEnfermedad(@PathParam("idEnfermedad")int idEnfermedad) {
	
		return medicoBean.obtenerExamenesDeEnfermedad(idEnfermedad);
		
	}*/
	
	@GET
	@Path("/examenesenfermedades/{idEnfermedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DtExamen> ExamenesDeEnfermedad(@PathParam("idEnfermedad")int idEnfermedad) {
	
		return medicoBean.examenesEnfermedad(idEnfermedad);
		
	}
	
	
	@GET
	@Path("/proveedoresexamen")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DtProveedorExamen> ObtenerProveedoresExamen()  {
	
		return medicoBean.ObtenerProveedoresExamen();
		
	}
	
	@POST
	@Path("/nuevocaso")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean nuevoCaso(DtCaso dtcaso)  {
			/*
			 http://localhost:8080/viruscontrol-web/rest/medico/nuevocaso
		Para crear un caso el json tiene que tener en el body los datos del caso asi:
		
	{
    "idDepartamento": 100,
    "idExamen": 100,
    "idEnfermedad": 100,
    "idPaciente": 102,
    "idMedico": 103,
    "idProveedorExamen": 300
}
			 
			*/
		return medicoBean.nuevoCaso(dtcaso.getIdDepartamento(), dtcaso.getIdExamen(), dtcaso.getIdEnfermedad(), dtcaso.getIdPaciente(), dtcaso.getIdMedico(),dtcaso.getIdProveedorExamen());
			
	}
	
	//http://localhost:8080/viruscontrol-web/rest/medico/dtcaso
	@GET
	@Path("/dtcaso")
	@Produces(MediaType.APPLICATION_JSON)
	public DtCaso unDtCaso()  {
			DtCaso dtc=new DtCaso(100, 100, 100, 102, 103,300);
			
		
			return dtc;
	}
	
	@GET
	@Path("/{username}/visita_pendiente/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<VisitaPendiente> getVisitasPendientes(@PathParam("username")String username){
			return medicoBean.getVisitaPendiente(username);
	}
	
	@PUT
	@Path("/{username}/visita_pendiente/{idVisitaPendiente}")
	public Response confirmarVisitaPendiente(@PathParam("username")String username, 
											 @PathParam("idVisitaPendiente")int idVisitaPendiente){
		try {
			
			boolean ok = medicoBean.confirmarVisitaPendiente(username, idVisitaPendiente);
			return Response.status(Status.OK).entity(ok).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	
	//http://localhost:8080/viruscontrol-web/rest/medico/enfermedadesaprobadas
	@GET
	@Path("/enfermedadesaprobadas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DtEnfermedad> enfermedadesAprobadas()  {
	
		return medicoBean.enfermerdadesAprobadas();
		
	}
	
	
	//http://localhost:8080/viruscontrol-web/rest/medico/departamentos
	@GET
	@Path("/departamentos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Departamento> obtenerDepartamentos()  {
	
		return medicoBean.obtenerDepartamentos();
		
	}
	
}
