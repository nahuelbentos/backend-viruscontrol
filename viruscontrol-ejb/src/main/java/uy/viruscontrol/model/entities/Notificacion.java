package uy.viruscontrol.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notificacion")
public class Notificacion {

	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String descripcion;
	
	@ManyToOne
	private Caso caso;
	
	@ManyToOne
	private PlanillaNotificacion planilla;

	protected Notificacion() {
	}

	public Notificacion(int id, String descripcion, Caso caso, PlanillaNotificacion planilla) {
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

	public PlanillaNotificacion getPlanilla() {
		return planilla;
	}

	public void setPlanilla(PlanillaNotificacion planilla) {
		this.planilla = planilla;
	}
	
	
	
}
