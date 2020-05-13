package uy.viruscontrol.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ciudadano")
public class Ciudadano extends Usuario {

	private static final long serialVersionUID = 1L;

	public Ciudadano() {
		super();
	}
	
	
	
}
