package uy.viruscontrol.bussines.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EnfermedadEnMapa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombreEnfermedad;
	private List<CasoEnMapa> casos;
	public String getNombreEnfermedad() {
		return nombreEnfermedad;
	}
	public void setNombreEnfermedad(String nombreEnfermedad) {
		this.nombreEnfermedad = nombreEnfermedad;
	}
	public List<CasoEnMapa> getCasos() {
		return casos;
	}
	public void setCasos(List<CasoEnMapa> casos) {
		this.casos = casos;
	}
	public EnfermedadEnMapa() {
		super();
		this.casos = new ArrayList<CasoEnMapa>();
	}
	
	public void addCaso(CasoEnMapa caso) {
		this.casos.add(caso);
	}

}
