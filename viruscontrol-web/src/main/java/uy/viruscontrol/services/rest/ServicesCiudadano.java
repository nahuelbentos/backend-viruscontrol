package uy.viruscontrol.services.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;

import uy.viruscontrol.bussines.interfaces.CiudadanoBeanLocal;
import uy.viruscontrol.bussines.interfaces.EnfermedadBeanLocal;
import uy.viruscontrol.bussines.interfaces.PrestadorBeanLocal;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.Sintoma;

@ApplicationScoped
@Path("/ciudadano")
public class ServicesCiudadano {
	@EJB private EnfermedadBeanLocal beanEnfermedad;
	@EJB private PrestadorBeanLocal beanPrestador;
	@EJB private CiudadanoBeanLocal beanCiudadano;
	private static ObjectMapper mapper;
	
	@GET
	@Path("/visita/sintomas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Sintoma> obtenerSintomas() {
		return beanEnfermedad.obtenerListaSintomas();
	}
	
	@GET
	@Path("/visita/medicos/{idCiudadano}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Medico> obtenerMedicosVisita(@PathParam("idCiudadano") int idCiudadano) {
		return beanPrestador.obtenerMedicosVisita(idCiudadano);
	}
	
	@POST
	@Path("/visita/confirmar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response confirmarVisita(@FormParam("idCiudadano") int idCiudadano, @FormParam("idMedico") int idMedico, @FormParam("fecha") Date fecha, @FormParam("sintomas") String sintomasJson) {
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
//			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
