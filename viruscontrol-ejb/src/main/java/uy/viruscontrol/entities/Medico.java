package uy.viruscontrol.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("medico")
public class Medico extends Usuario {

	private static final long serialVersionUID = 1L;
	
	private boolean conectado;

	public Medico() {
		super();
	}

	public boolean isConectado() {
		return conectado;
	}

	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}
	
}
