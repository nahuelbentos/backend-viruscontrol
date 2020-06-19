package uy.viruscontrol.model.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ubicacion")
public class Ubicacion implements Serializable {
	private static final long serialVersionUID = 2268005279751168496L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String latitud;
	@Column
	private String longitud;
	@ManyToOne
	private Ciudadano ciudadano;
	@Column
	private Calendar fecha;


	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public Ubicacion() {
	}

	public Ubicacion(int id, String latitud, String longitud, Ciudadano ciudadano, Calendar fecha) {
		super();
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
		this.ciudadano = ciudadano;
		this.fecha = fecha;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public Ciudadano getCiudadano() {
		return ciudadano;
	}

	public void setCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	} 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
