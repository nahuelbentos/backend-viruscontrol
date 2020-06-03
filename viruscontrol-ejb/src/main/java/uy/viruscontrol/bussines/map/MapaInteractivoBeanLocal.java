package uy.viruscontrol.bussines.map;

import javax.ejb.Local;

@Local
public interface MapaInteractivoBeanLocal {
	
	public void loadCasosOnMapa();
	
	public MapaInteractivo getMapa();

}
