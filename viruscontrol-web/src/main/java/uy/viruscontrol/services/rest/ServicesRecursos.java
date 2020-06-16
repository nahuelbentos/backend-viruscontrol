 package uy.viruscontrol.services.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.viruscontrol.bussines.interfaces.EnfermedadBeanLocal;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorRecursoLocal;
import uy.viruscontrol.model.dao.interfaces.EnfermedadDAOLocal;
import uy.viruscontrol.model.dao.interfaces.RecursoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.TipoRecursoDAOLocal;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.TipoRecurso;
import uy.viruscontrol.utils.DtRecursosProveedor;

@ApplicationScoped
@Path("/recursos")
public class ServicesRecursos {

	@EJB private EnfermedadBeanLocal enfermedadBeanLocal;
	@EJB private TipoRecursoDAOLocal daoTipoRecursoLocal;
	@EJB private RecursoDAOLocal daoRecursoLocal;
	@EJB private EnfermedadDAOLocal daoEnfermedadLocal;
	@EJB private ServiceAgentProveedorRecursoLocal saProvRec;
	
	//http://localhost:8080/viruscontrol-web/rest/recursos/obtenerRecursos
	@GET
	@Path("/obtener")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recurso> obtenerRecursos(){
		return enfermedadBeanLocal.obtenerRecursosDisponibles();
	}
	
	//http://localhost:8080/viruscontrol-web/rest/recursos/obtenerRecursosPorTipoRecurso/100	
	@GET
	@Path("/obtener/porTipo/{idTipoRecurso}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recurso> obtenerRecursosPorTipoRecurso(@PathParam("idTipoRecurso") int idTipoRecurso){
		TipoRecurso tr = daoTipoRecursoLocal.findById(idTipoRecurso);
		return enfermedadBeanLocal.obtenerRecursosPorTipoRecurso(tr);
	}
	
	//http://localhost:8080/viruscontrol-web/rest/recursos/obtener/porEnfermedad/100	
	@GET
	@Path("/obtener/porEnfermedad/{idEnfermedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recurso> obtenerRecursosPorEnfermedad(@PathParam("idEnfermedad") int idEnfermedad){
		
		return enfermedadBeanLocal.obtenerRecursoPorEnfermedad(idEnfermedad);
	}
	
	@GET
	@Path("/disponibles/ciudad/{ciudad}/barrio/{barrio}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DtRecursosProveedor> obtenerRecursosDisponiblesPorCiudadBarrio(@PathParam("ciudad") String ciudad,
																				@PathParam("barrio") String barrio){
		ciudad = ciudad.replaceAll(" ", "%20");
		barrio = barrio.replaceAll(" ", "%20");
		return saProvRec.getRecursosDisponiblesPorCiudadBarrio(ciudad, barrio);
	}
	
}
