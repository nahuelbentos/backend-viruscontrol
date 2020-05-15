package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("ciudadano")
public class Ciudadano extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private PrestadoraSalud prestadoraSalud;
	
	public Ciudadano() {
		super();
	}
	
	
	
}
