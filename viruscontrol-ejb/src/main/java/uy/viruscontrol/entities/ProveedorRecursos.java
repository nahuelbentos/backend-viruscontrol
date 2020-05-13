package uy.viruscontrol.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("RECURSO")
public class ProveedorRecursos extends Proveedor implements Serializable {
	private static final long serialVersionUID = -1828786797063019091L;
	
	public ProveedorRecursos() {
		super();
	}

	public ProveedorRecursos(int id, String nombre) {
		super(id, nombre);
	}

}
