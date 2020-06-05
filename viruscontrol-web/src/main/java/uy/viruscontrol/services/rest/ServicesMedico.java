package uy.viruscontrol.services.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import uy.viruscontrol.bussines.interfaces.MedicoBeanLocal;
import uy.viruscontrol.bussines.interfaces.SessionBeanLocal;
import uy.viruscontrol.model.entities.Departamento;
import uy.viruscontrol.model.entities.Usuario;
import uy.viruscontrol.utils.DtCaso;
import uy.viruscontrol.utils.DtEnfermedad;

@ApplicationScoped
@Path("/medico")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicesMedico {

	@EJB private MedicoBeanLocal medicoBean;
	@EJB private SessionBeanLocal beanSesion;

	@GET
	@Path("/ciudadanos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerCiudadanos(@HeaderParam("authorization") String token) {
		if (beanSesion.validateAuthentication(token))
			return Response.status(Status.OK).entity(medicoBean.mostrarCiudadanos()).build();
		else
			return Response.status(Status.UNAUTHORIZED).build();
	}
	
	@GET
	@Path("/examenesenfermedades/{idEnfermedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ExamenesDeEnfermedad(@HeaderParam("authorization") String token, 
													@PathParam("idEnfermedad")int idEnfermedad) {
		if (beanSesion.validateAuthentication(token))
			return Response.status(Status.OK).entity(medicoBean.examenesEnfermedad(idEnfermedad)).build();
		else
			return Response.status(Status.UNAUTHORIZED).build();
	}
	
	@GET
	@Path("/proveedoresexamen")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ObtenerProveedoresExamen(@HeaderParam("authorization") String token)  {
		if (beanSesion.validateAuthentication(token))
			return Response.status(Status.OK).entity(medicoBean.ObtenerProveedoresExamen()).build();
		else
			return Response.status(Status.UNAUTHORIZED).build();
	}
	
	@POST
	@Path("/nuevocaso")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response nuevoCaso(DtCaso dtcaso, @HeaderParam("authorization") String token)  {
		if (beanSesion.validateAuthentication(token))
			return Response.status(Status.OK).entity(medicoBean.nuevoCaso(dtcaso.getIdDepartamento(), dtcaso.getIdExamen(), dtcaso.getIdEnfermedad(), dtcaso.getIdPaciente(), dtcaso.getIdMedico(),dtcaso.getIdProveedorExamen())).build();
		else
			return Response.status(Status.UNAUTHORIZED).build();
	}
	
	@GET
	@Path("/visita_pendiente/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getVisitasPendientes(@HeaderParam("authorization") String token){
		if (beanSesion.validateAuthentication(token)) {
			Usuario u = beanSesion.getUsuarioLogueado(token);
			if (u != null)
				return Response.status(Status.OK).entity(medicoBean.getVisitaPendiente(u.getUsername())).build();
			else
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} else
			return Response.status(Status.UNAUTHORIZED).build();
	}
	
	@PUT
	@Path("/visita_pendiente/{idVisitaPendiente}")
	public Response confirmarVisitaPendiente(@HeaderParam("authorization") String token, 
											 @PathParam("idVisitaPendiente")int idVisitaPendiente){
		if (beanSesion.validateAuthentication(token)) {
			try {
				Usuario u = beanSesion.getUsuarioLogueado(token);
				boolean ok = medicoBean.confirmarVisitaPendiente(u.getUsername(), idVisitaPendiente);
				return Response.status(Status.OK).entity(ok).build();
			} catch (Exception e) {
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
			}
		} else
			return Response.status(Status.UNAUTHORIZED).build();
	}
	
	@GET
	@Path("/enfermedadesaprobadas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DtEnfermedad> enfermedadesAprobadas()  {
		return medicoBean.enfermerdadesAprobadas();
	}
	
	@GET
	@Path("/departamentos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Departamento> obtenerDepartamentos()  {
		return medicoBean.obtenerDepartamentos();
	}
}
