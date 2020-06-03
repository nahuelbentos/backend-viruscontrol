package uy.viruscontrol.bussines.map;

import java.io.Serializable;
import java.util.List;

public class MapaInteractivo implements Serializable{

	private static final long serialVersionUID = -8522164860357673768L;
	
	private String ultimaActualizacion;
	private List<DepartamentoEnMapa> mapa;
	public MapaInteractivo() {
		super();
	}
	public String getUltimaActualizacion() {
		return ultimaActualizacion;
	}
	public void setUltimaActualizacion(String ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}
	public List<DepartamentoEnMapa> getMapa() {
		return mapa;
	}
	public void setMapa(List<DepartamentoEnMapa> mapa) {
		this.mapa = mapa;
	}
	
}
