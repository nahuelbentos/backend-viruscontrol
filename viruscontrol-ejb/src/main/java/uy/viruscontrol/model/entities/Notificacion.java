package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notificacion")
public class Notificacion implements Serializable {
	private static final long serialVersionUID = 7319096381795282022L;

	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String descripcion;
	
	@ManyToOne
	private Caso caso;
	
	@ManyToOne
	private PlantillaNotificacion planilla;

	public Notificacion() {
	}

	public Notificacion(int id, String descripcion, Caso caso, PlantillaNotificacion planilla) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.caso = caso;
		this.planilla = planilla;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Caso getCaso() {
		return caso;
	}

	public void setCaso(Caso caso) {
		this.caso = caso;
	}

	public PlantillaNotificacion getPlanilla() {
		return planilla;
	}

	public void setPlanilla(PlantillaNotificacion planilla) {
		this.planilla = planilla;
	}
	
	
	
}
