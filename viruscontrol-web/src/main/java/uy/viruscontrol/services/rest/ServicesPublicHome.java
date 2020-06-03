package uy.viruscontrol.services.rest;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import uy.viruscontrol.bussines.map.MapaInteractivo;
import uy.viruscontrol.bussines.map.MapaInteractivoBeanLocal;

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

}
