package uy.gub.rucaf.entities;

import java.io.Serializable;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 6727621416541766299L;
	
	private int documento;
	private String nombreCompleto;
	private PrestadoraSalud prestadora;

	public Usuario() {
		super();
	}

	public Usuario(int documento, String nombreCompleto, PrestadoraSalud prestadora) {
		super();
		this.documento = documento;
		this.nombreCompleto = nombreCompleto;
		this.setPrestadora(prestadora);
	}

	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public PrestadoraSalud getPrestadora() {
		return prestadora;
	}

	public void setPrestadora(PrestadoraSalud prestadora) {
		this.prestadora = prestadora;
	}

	@Override
	public String toString() {
		return documento + ";" + nombreCompleto + ";" + prestadora.getId() + ";";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + documento;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (documento != other.documento)
			return false;
		return true;
	}

}
