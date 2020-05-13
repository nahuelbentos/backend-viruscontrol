package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("gerente")
public class Gerente extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	public Gerente() {
		super();
	}
	
}
