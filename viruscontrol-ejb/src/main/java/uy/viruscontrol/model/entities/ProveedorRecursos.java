package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RECURSO")
public class ProveedorRecursos extends Proveedor implements Serializable {
	private static final long serialVersionUID = -1828786797063019091L;
	
	public ProveedorRecursos() {
		super();
	}

	public ProveedorRecursos(int id, String nombre,String direccion,String barrio,String rangoHorario) {
		super(id, nombre, direccion, barrio, rangoHorario);
	}

}
