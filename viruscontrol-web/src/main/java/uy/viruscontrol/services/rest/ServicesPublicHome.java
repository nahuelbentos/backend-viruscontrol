package uy.viruscontrol.services.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import uy.viruscontrol.bussines.enumerated.TipoCaso;
import uy.viruscontrol.bussines.map.MapaInteractivo;
import uy.viruscontrol.bussines.map.MapaInteractivoBeanLocal;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorRecursoLocal;
import uy.viruscontrol.model.dao.interfaces.TipoRecursoDAOLocal;
import uy.viruscontrol.model.entities.TipoRecurso;

@ApplicationScoped
public class ServicesPublicHome implements IServicesPublicHome {

	@EJB 
	private MapaInteractivoBeanLocal mapa;
	
	@Override
	public MapaInteractivo getMapa() {
		
		if (mapa == null)
			return null;
		
		return mapa.getMapa();
	}

	@Override
	public List<TipoCaso> getEstadoEnfermedad() {
		List<TipoCaso> tipos = new ArrayList<TipoCaso>();
		tipos.add(TipoCaso.CONFIRMADO);
		tipos.add(TipoCaso.SOSPECHOSO);
		tipos.add(TipoCaso.EXPOSICION);
		tipos.add(TipoCaso.RECUPERADO);
		return tipos;
	}

	
	// No hagan esto en su casa
	@EJB private TipoRecursoDAOLocal tipoDao;
	@Override
	public List<TipoRecurso> getTiposRecursos() {
		return tipoDao.findAll();
	}

	@EJB private ServiceAgentProveedorRecursoLocal sagRecursos;
	@Override
	public List<String> getBarrios() {
		return sagRecursos.getListadoBarrios();
	}
	
	

}
