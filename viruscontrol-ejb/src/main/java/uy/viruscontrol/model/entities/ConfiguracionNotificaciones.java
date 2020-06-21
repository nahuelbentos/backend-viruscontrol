package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import uy.viruscontrol.bussines.enumerated.TipoNotificacion;

@Entity
@Table(name = "configuracion_notificaciones")
public class ConfiguracionNotificaciones implements Serializable {
	private static final long serialVersionUID = -3907362867774686137L;
	
	@Id
	@Enumerated(EnumType.STRING)
	private TipoNotificacion tipo;
	
	@Column
	private boolean notificarGerentes;
	
	@Column
	private boolean notificarCiudadano;

	@Column
	private boolean notificarMedico;
	
	public ConfiguracionNotificaciones() {
		super();
	}

	public ConfiguracionNotificaciones(TipoNotificacion tipo, boolean notificarGerentes, boolean notificarCiudadano,
			boolean notificarMedico) {
		super();
		this.tipo = tipo;
		this.notificarGerentes = notificarGerentes;
		this.notificarCiudadano = notificarCiudadano;
		this.notificarMedico = notificarMedico;
	}

	public TipoNotificacion getTipo() {
		return tipo;
	}

	public void setTipo(TipoNotificacion tipo) {
		this.tipo = tipo;
	}

	public boolean isNotificarGerentes() {
		return notificarGerentes;
	}

	public void setNotificarGerentes(boolean notificarGerentes) {
		this.notificarGerentes = notificarGerentes;
	}

	public boolean isNotificarCiudadano() {
		return notificarCiudadano;
	}

	public void setNotificarCiudadano(boolean notificarCiudadano) {
		this.notificarCiudadano = notificarCiudadano;
	}

	public boolean isNotificarMedico() {
		return notificarMedico;
	}

	public void setNotificarMedico(boolean notificarMedico) {
		this.notificarMedico = notificarMedico;
	}

	@Override
	public String toString() {
		return "ConfiguracionNotificaciones [tipo=" + tipo + ", notificarGerentes=" + notificarGerentes
				+ ", notificarCiudadano=" + notificarCiudadano + ", notificarMedico=" + notificarMedico + "]";
	}
	
	

}
