package uy.viruscontrol.bussines.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoEnMapa implements Serializable {

	private static final long serialVersionUID = 5138444689693397215L;
	
	private String nombre;
	private List<EnfermedadEnMapa> enfermedades;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<EnfermedadEnMapa> getEnfermedades() {
		return enfermedades;
	}
	public void setEnfermedades(List<EnfermedadEnMapa> enfermedades) {
		this.enfermedades = enfermedades;
	}
	public DepartamentoEnMapa() {
		super();
		this.enfermedades = new ArrayList<EnfermedadEnMapa>();
	}
	public void addEnfermedad(EnfermedadEnMapa enfermedad) {
		this.enfermedades.add(enfermedad);
	}
	
	
	

	
}
