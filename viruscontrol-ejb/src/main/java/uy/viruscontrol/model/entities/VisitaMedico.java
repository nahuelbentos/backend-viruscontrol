package uy.viruscontrol.model.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(IdVisitaMedico.class)
@Table(name = "visita_medico")
public class VisitaMedico implements Serializable {
	private static final long serialVersionUID = 771155363015049522L;

	@Id
	@Column(name = "id_medico")
	private Medico medico;
	
	@Id
	@Column(name = "id_ciudadano")
	private Ciudadano ciudadano;
	
	@Id
	@Column(name = "fecha_asignacion")
	private Calendar fechaAsignacion;

	public VisitaMedico() {
		super();
	}

	public VisitaMedico(Medico medico, Ciudadano ciudadano, Calendar fechaAsignacion) {
		super();
		this.medico = medico;
		this.ciudadano = ciudadano;
		this.fechaAsignacion = fechaAsignacion;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Ciudadano getCiudadano() {
		return ciudadano;
	}

	public void setCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	}

	public Calendar getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Calendar fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
	
}
