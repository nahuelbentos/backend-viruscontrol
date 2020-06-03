package uy.viruscontrol.bussines.map;

import java.io.Serializable;

import uy.viruscontrol.bussines.enumerated.TipoCaso;

public class CasoEnMapa implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoCaso casoTipo;
	private int cantidad;
	public TipoCaso getCasoTipo() {
		return casoTipo;
	}
	public void setCasoTipo(TipoCaso casoTipo) {
		this.casoTipo = casoTipo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public CasoEnMapa() {
		super();
	}
	
	
	
}
