package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("administrador")
public class Administrador extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	public Administrador() {
		super();
	}
	
}
