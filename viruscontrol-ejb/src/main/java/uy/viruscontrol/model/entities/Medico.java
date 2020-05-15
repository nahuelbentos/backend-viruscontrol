package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("medico")
public class Medico extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private PrestadoraSalud prestadoraSalud;
	
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
