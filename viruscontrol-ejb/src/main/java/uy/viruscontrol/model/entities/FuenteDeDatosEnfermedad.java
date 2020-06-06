package uy.viruscontrol.model.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.FuenteDeDatos;
import uy.viruscontrol.utils.DtFuenteDeDatosEnfermedad;

@Entity
@Table(name="fuente_de_datos_enfermedad")
public class FuenteDeDatosEnfermedad implements Serializable {
	private static final long serialVersionUID = 2476275041326051752L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false)
	private Enfermedad enfermedad;
	@Column(nullable = false)
	private FuenteDeDatos fuente;
	private String filtros;
	private String url;

	public FuenteDeDatosEnfermedad() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public Enfermedad getEnfermedad() {
		return this.enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}   
	public FuenteDeDatos getFuente() {
		return this.fuente;
	}

	public void setFuente(FuenteDeDatos fuente) {
		this.fuente = fuente;
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
	
	public DtFuenteDeDatosEnfermedad getDt() {
		DtFuenteDeDatosEnfermedad dt = new DtFuenteDeDatosEnfermedad();
		dt.setId(this.getId());
		dt.setEnfermedad(this.getEnfermedad().getId());
		dt.setEnfermedadNombre(this.getEnfermedad().getNombre());
		dt.setFuente(this.getFuente().getId());
		dt.setFiltros(this.getFiltros());
		dt.setUrl(this.getUrl());
		return dt;
	}
}
