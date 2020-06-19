package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "suscripcion")
public class Suscripcion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6110927408900541234L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Ciudadano ciudadano;
	
	@Column(name="barrio")
	private String	barrio;
	
	@Column(name="recurso")
	private String recurso;
	
	@Column(name="notificado")
	private boolean notificado;
	
	
	public Suscripcion() {
		super();
	}
	
	public Suscripcion(int id, Ciudadano ciudadano, String barrio, String recurso) {
		super();
		this.id = id;
		this.ciudadano = ciudadano;
		this.barrio = barrio;
		this.recurso = recurso;
	}

	

	public Ciudadano getCiudadano() {
		return ciudadano;
	}



	public void setCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	}



	public String getBarrio() {
		return barrio;
	}



	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}



	public String getRecurso() {
		return recurso;
	}



	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	public boolean isNotificado() {
		return notificado;
	}

	public void setNotificado(boolean notificado) {
		this.notificado = notificado;
	}
	
	
	
	
	
}
