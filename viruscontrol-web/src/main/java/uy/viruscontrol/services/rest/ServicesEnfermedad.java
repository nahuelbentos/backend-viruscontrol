package uy.viruscontrol.services.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.viruscontrol.bussines.interfaces.FuenteDatosBeanLocal;
import uy.viruscontrol.model.dao.interfaces.EnfermedadDAOLocal;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.FuenteDeDatosEnfermedad;
import uy.viruscontrol.utils.DtFuenteDeDatosEnfermedad;

@ApplicationScoped
@Path("/enfermedad")
public class ServicesEnfermedad {
	@EJB private FuenteDatosBeanLocal beanFuenteDatos;
	@EJB private EnfermedadDAOLocal daoEnfermedad;
	
	@GET
	@Path("/fuenteDatos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DtFuenteDeDatosEnfermedad> obtenerTodasFuentesDeDatosEnfermedad(){
		List<FuenteDeDatosEnfermedad> ret = beanFuenteDatos.obtenerTodosFuenteDeDatosEnfermedad();
		List<DtFuenteDeDatosEnfermedad> lista = new ArrayList<DtFuenteDeDatosEnfermedad>();
		for (FuenteDeDatosEnfermedad it : ret) {
			lista.add(it.getDt());
		}
		return (lista != null) ? lista : new ArrayList<DtFuenteDeDatosEnfermedad>();
	}
	
	@GET
	@Path("/fuenteDatos/{idEnfermedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DtFuenteDeDatosEnfermedad> obtenerTodasFuentesDeDatosEnfermedad(@PathParam("idEnfermedad") int idEnfermedad){
		Enfermedad e = daoEnfermedad.findById(idEnfermedad);
		List<FuenteDeDatosEnfermedad> ret = beanFuenteDatos.obtenerFuentesPorEnfermedad(e);
		List<DtFuenteDeDatosEnfermedad> lista = new ArrayList<DtFuenteDeDatosEnfermedad>();
		for (FuenteDeDatosEnfermedad it : ret) {
			lista.add(it.getDt());
		}
		return (lista != null) ? lista : new ArrayList<DtFuenteDeDatosEnfermedad>();
	}

}
