package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RECURSO")
public class ProveedorRecursos extends Proveedor implements Serializable {
	private static final long serialVersionUID = -1828786797063019091L;
	
	@Column(name="codigo_periferico")
	private String codigoPeriferico;
	
	
	public String getCodigoPeriferico() {
		return codigoPeriferico;
	}

	public void setCodigoPeriferico(String codigoPeriferico) {
		this.codigoPeriferico = codigoPeriferico;
	}

	public ProveedorRecursos() {
		super();
	}

	public ProveedorRecursos(int id, String nombre,String direccion,String barrio,String rangoHorario) {
		super(id, nombre, direccion, barrio, rangoHorario);
	}

}
