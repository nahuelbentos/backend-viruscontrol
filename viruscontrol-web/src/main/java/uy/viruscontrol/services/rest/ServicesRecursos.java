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
import uy.viruscontrol.model.dao.interfaces.RecursoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.TipoRecursoDAOLocal;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.TipoRecurso;

@ApplicationScoped
@Path("/recursos")
public class ServicesRecursos {

	@EJB EnfermedadBeanLocal enfermedadBeanLocal;
	@EJB TipoRecursoDAOLocal daoTipoRecursoLocal;
	@EJB RecursoDAOLocal daoRecursoLocal;
	
	
	//http://localhost:8080/viruscontrol-web/rest/recursos/obtenerRecursos
	@GET
	@Path("/obtener")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recurso> obtenerRecursos(){
		return enfermedadBeanLocal.obtenerRecursosDisponibles();
	}
	
	
	
	//http://localhost:8080/viruscontrol-web/rest/recursos/obtenerRecursosPorTipoRecurso/100	
	@GET
	@Path("obtener/porTipo/{idTipoRecurso}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recurso> obtenerRecursosPorTipoRecurso(@PathParam("idTipoRecurso") int idTipoRecurso){
		TipoRecurso tr = daoTipoRecursoLocal.findById(idTipoRecurso);
		return enfermedadBeanLocal.obtenerRecursosPorTipoRecurso(tr);
	}
	
}
