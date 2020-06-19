package uy.viruscontrol.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uy.viruscontrol.model.entities.TipoRecurso;

public class DtRecurso implements Serializable {
	private static final long serialVersionUID = 8131079399357840365L;
	
	private int id;
	private String nombre;
	private TipoRecurso tipoRecurso;
	private List<DtEnfermedad> enfermedades = new ArrayList<>();
	private int stock;
	
	public DtRecurso() {
		super();
	}

	public DtRecurso(int id, String nombre, TipoRecurso tipoRecurso, List<DtEnfermedad> enfermedades, int stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipoRecurso = tipoRecurso;
		this.enfermedades = enfermedades;
		this.setStock(stock);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoRecurso getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(TipoRecurso tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public List<DtEnfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(List<DtEnfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
