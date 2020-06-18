package uy.viruscontrol.services.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.databind.ObjectMapper;

import uy.viruscontrol.bussines.interfaces.CiudadanoBeanLocal;
import uy.viruscontrol.bussines.interfaces.EnfermedadBeanLocal;
import uy.viruscontrol.bussines.interfaces.PrestadorBeanLocal;
import uy.viruscontrol.bussines.interfaces.SessionBeanLocal;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorExamenLocal;
import uy.viruscontrol.model.dao.interfaces.SuscripcionDAOLocal;
import uy.viruscontrol.model.entities.Sintoma;
import uy.viruscontrol.model.entities.Suscripcion;
import uy.viruscontrol.utils.DtSuscripcion;

@ApplicationScoped
@Path("/ciudadano")
public class ServicesCiudadano {
	@EJB private EnfermedadBeanLocal beanEnfermedad;
	@EJB private PrestadorBeanLocal beanPrestador;
	@EJB private CiudadanoBeanLocal beanCiudadano;
	@EJB private SessionBeanLocal beanSesion;
	@EJB private ServiceAgentProveedorExamenLocal saProvExamenLocal;
	@EJB private SuscripcionDAOLocal daoSuscripcion;
	
	private static ObjectMapper mapper;
	
	@GET
	@Path("/visita/sintomas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerSintomas(@HeaderParam("authorization") String token) {
		if (beanSesion.validateAuthentication(token))
			return Response.status(Status.OK).entity(beanEnfermedad.obtenerListaSintomas()).build();
		else
			return Response.status(Status.UNAUTHORIZED).build();
	}
	
	@GET
	@Path("/visita/medicos/{idCiudadano}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerMedicosVisita(@HeaderParam("authorization") String token,
											@PathParam("idCiudadano") int idCiudadano) {
		if (beanSesion.validateAuthentication(token))
			return Response.status(Status.OK).entity(beanPrestador.obtenerMedicosVisita(idCiudadano)).build();
		else
			return Response.status(Status.UNAUTHORIZED).build();
	}
	
	@POST
	@Path("/visita/confirmar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response confirmarVisita(@HeaderParam("authorization") String token,
										@FormParam("idCiudadano") int idCiudadano, 
										@FormParam("idMedico") int idMedico, 
										@FormParam("fecha") Date fecha, 
										@FormParam("sintomas") String sintomasJson) {
		if (beanSesion.validateAuthentication(token)) {
			mapper = new ObjectMapper();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Calendar fec = Calendar.getInstance();
				System.out.println("Fecha ingresada: "+sdf.format(fecha));
				fec.setTime(fecha);
				System.out.println("Listado de sintomas");
				List<Sintoma> sintomas = mapper.readValue(sintomasJson, mapper.getTypeFactory().constructCollectionType(List.class, Sintoma.class));
				for (Sintoma sintoma : sintomas) {
					System.out.println("sintoma: " + sintoma.getNombre());
				}
				boolean ok = beanCiudadano.solicitarMedicoADomicilio(idCiudadano, idMedico, fec, sintomas);
				return Response.status(Status.OK).entity(ok).build();
			} catch (Exception e) {
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
			}
		} else
			return Response.status(Status.UNAUTHORIZED).build();
	}
	
	
	@GET
	@Path("/obtenerexamenes/{idCiudadano}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerExamenesCiudadano(@HeaderParam("authorization") String token,@PathParam("idCiudadano") int idCiudadano){
		
		if (beanSesion.validateAuthentication(token))
			return Response.status(Status.OK).entity(beanCiudadano.obtenerExamenesCiudadano(idCiudadano)).build();
		else
			return Response.status(Status.UNAUTHORIZED).build();
	}
	//http://localhost:8080/viruscontrol-web/rest/ciudadano/obtenerBarrios
	@GET
	@Path("/obtenerBarrios")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> obtenerBarrios(){
		return beanCiudadano.obtenerBarrios();
	}
	//http://localhost:8080/viruscontrol-web/rest/ciudadano/obtenerCiudades
	@GET
	@Path("/obtenerCiudades")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> obtenerCiudades(){
		return beanCiudadano.obtenerCiudades();
	}
	
	@POST
	@Path("/suscribirseARecurso")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean suscribirseARecurso(DtSuscripcion s) {
		/*
   		{
        "ciudadanoId": 102,
        "barrio": "Centro",
        "recurso": "alcohol en gel"
    	}
		 */
		System.out.println("ciudadanoId "+s.getCiudadanoId());
		System.out.println("barrio "+s.getBarrio());
		System.out.println("recurso "+s.getRecurso());
		beanCiudadano.suscribirseARecurso(s.getCiudadanoId(), s.getBarrio(), s.getRecurso());
		
		return true;
	}
	
	@GET
	@Path("/suscripciones")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DtSuscripcion> obtenerSuscripcion(){
	List<Suscripcion> suscripciones=daoSuscripcion.findAll();
	List<DtSuscripcion> listdts=new ArrayList<DtSuscripcion>();
	
		for(Suscripcion s:suscripciones) {
			DtSuscripcion dts=new DtSuscripcion();
			dts.setBarrio(s.getBarrio());
			dts.setCiudadanoId(s.getCiudadano().getIdUsuario());
			dts.setRecurso(s.getRecurso());
			listdts.add(dts);
		}
		return listdts;
		
	}
	
}
