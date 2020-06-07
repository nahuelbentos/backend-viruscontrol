package uy.viruscontrol.utils;

import java.io.Serializable;
import java.lang.String;

public class DtFuenteDeDatosEnfermedad implements Serializable {
	private static final long serialVersionUID = -8039743536740350610L;
	
	private int id;
	private int enfermedadId;
	private String enfermedadNombre;
	private int fuenteId;
	private String filtros;
	private String url;

	public DtFuenteDeDatosEnfermedad() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public int getEnfermedad() {
		return this.enfermedadId;
	}

	public void setEnfermedad(int enfermedadId) {
		this.enfermedadId = enfermedadId;
	}   
	public int getFuente() {
		return this.fuenteId;
	}

	public void setFuente(int fuenteId) {
		this.fuenteId = fuenteId;
	}   
	public String getFiltros() {
		return this.filtros;
	}

	public void setFiltros(String filtros) {
		this.filtros = filtros;
	}   
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getEnfermedadNombre() {
		return enfermedadNombre;
	}
	public void setEnfermedadNombre(String enfermedadNombre) {
		this.enfermedadNombre = enfermedadNombre;
	}
   
}
