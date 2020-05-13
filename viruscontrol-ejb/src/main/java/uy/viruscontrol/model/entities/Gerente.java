package uy.viruscontrol.model.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("gerente")
public class Gerente extends Usuario {

	private static final long serialVersionUID = 1L;

	public Gerente() {
		super();
	}
	
}
