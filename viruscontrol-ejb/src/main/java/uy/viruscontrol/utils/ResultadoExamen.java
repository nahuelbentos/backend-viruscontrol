package uy.viruscontrol.utils;

import java.io.Serializable;

import uy.viruscontrol.model.entities.EstadoExamen;

public class ResultadoExamen implements Serializable{
	
	private static final long serialVersionUID = -4337801077792397590L;

	private EstadoExamen resultado;
	
	private String pathPdf;

	public EstadoExamen getResultado() {
		return resultado;
	}

	public void setResultado(EstadoExamen resultado) {
		this.resultado = resultado;
	}

	public String getPathPdf() {
		return pathPdf;
	}

	public void setPathPdf(String pathPdf) {
		this.pathPdf = pathPdf;
	}

	public ResultadoExamen() {
		super();
	}

}
