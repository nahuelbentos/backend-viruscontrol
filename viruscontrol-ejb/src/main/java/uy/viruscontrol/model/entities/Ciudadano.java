package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ciudadano")
public class Ciudadano extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	public Ciudadano() {
		super();
	}
	
	
	
}
