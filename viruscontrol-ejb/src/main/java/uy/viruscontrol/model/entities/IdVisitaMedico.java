package uy.viruscontrol.model.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;

public class IdVisitaMedico implements Serializable {
	private static final long serialVersionUID = 2553168422674201916L;
	
	@Column(columnDefinition = "integer")
	private Medico medico;
	@Column(columnDefinition = "integer")
	private Ciudadano ciudadano;
	private Calendar fechaAsignacion;
	
	public IdVisitaMedico() {
		super();	
	}

	public IdVisitaMedico(Medico medico, Ciudadano ciudadano, Calendar fechaAsignacion) {
		super();
		this.medico = medico;
		this.ciudadano = ciudadano;
		this.setFechaAsignacion(fechaAsignacion);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciudadano == null) ? 0 : ciudadano.hashCode());
		result = prime * result + ((fechaAsignacion == null) ? 0 : fechaAsignacion.hashCode());
		result = prime * result + ((medico == null) ? 0 : medico.hashCode());
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
		IdVisitaMedico other = (IdVisitaMedico) obj;
		if (ciudadano == null) {
			if (other.ciudadano != null)
				return false;
		} else if (!ciudadano.equals(other.ciudadano))
			return false;
		if (fechaAsignacion == null) {
			if (other.fechaAsignacion != null)
				return false;
		} else if (!fechaAsignacion.equals(other.fechaAsignacion))
			return false;
		if (medico == null) {
			if (other.medico != null)
				return false;
		} else if (!medico.equals(other.medico))
			return false;
		return true;
	}
	
	

}
