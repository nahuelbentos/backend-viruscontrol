package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EXAMEN")
public class ProveedorExamen extends Proveedor implements Serializable {
	private static final long serialVersionUID = 2352742454735051987L;

	public ProveedorExamen() {
		super();
	}

	public ProveedorExamen(int id, String nombre,String direccion,String barrio,String rangoHorario) {
		super(id, nombre, direccion, barrio, rangoHorario);
	}

}
