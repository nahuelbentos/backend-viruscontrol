package uy.viruscontrol.model.entities;

import java.io.Serializable;
import java.util.Calendar;

public class IdVisitaMedico implements Serializable {
	private static final long serialVersionUID = 2553168422674201916L;
	
	private Medico medico;
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

}
