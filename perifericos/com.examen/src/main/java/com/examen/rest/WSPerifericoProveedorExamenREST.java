package com.examen.rest;

import java.util.ArrayList;
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

import com.examen.beans.PerifericoProveedorExamenLocal;
import com.examen.entities.EstadoExamen;
import com.examen.entities.Examen;
import com.examen.entities.ProveedorExamen;
import com.examen.utils.DtExamen;

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
	@Path("/proveedores/{idEnfermedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProveedorExamen> obtenerProveedores(@PathParam("idEnfermedad") int idEnfermedad) {
		return provEx.findAllByEnfermedad(idEnfermedad);
	}
	
	@GET
	@Path("/examenes/{idEnfermedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DtExamen> obtenerExamenesParaUnaEnfermedad(@PathParam("idEnfermedad") int idEnfermedad){
		/* 
		 * Se creó el DtExamen porque java no logra serializar la clase Enfermedad (se asume que por 
		 * el id embebido, ya que el resto de las propiedades se han usado en otras clases y no ha 
		 * habido problemas al serializas)
		 * De esta manera, en el DtExamen se setean solo valores de interes como el id del examen y la enfermedad asociada
		 */
		List<Examen> temp = provEx.listaExamenesParaEnfermedad(idEnfermedad);
		List<DtExamen> lista = new ArrayList<DtExamen>();
		for (Examen e : temp) {
			lista.add(e.getDt());
		}
		return lista;
	}
	
	@POST
	@Path("/examen")
	@Produces(MediaType.APPLICATION_JSON)
	public DtExamen altaDeExamen(@FormParam("idPaciente") int idPaciente, 
									@FormParam("idExamen") int idExamen,
									@FormParam("idMedico") int idMedico) {
//		System.out.println("["+WSPerifericoProveedorExamenREST.class.getName()+"] Paciente: "+idPaciente+", Examen: "+idExamen+", Medico: "+idMedico);
		// debería devolver un id caso, pero no quiero romper el sequence de hibernate, así que no lo devuelvo.
		return provEx.solicitarAltaExamen(idPaciente, idExamen, idMedico).getDt();
	}
	
	@GET
	@Path("/resultados/{idCaso}")
	@Produces(MediaType.APPLICATION_JSON)
	public EstadoExamen obtenerResultadoExamen(@PathParam("idCaso") int idCaso) {
		return provEx.resultadoExamen(idCaso);
	}
}
