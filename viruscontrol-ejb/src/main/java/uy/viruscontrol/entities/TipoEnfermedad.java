package uy.viruscontrol.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoenfermedad")
public class TipoEnfermedad {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTipoEnfermedad;
	private String nombre;
	
	
	public TipoEnfermedad() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TipoEnfermedad(int idTipoEnfermedad, String nombre) {
		super();
		this.idTipoEnfermedad = idTipoEnfermedad;
		this.nombre = nombre;
	}


	public int getidTipoEnfermedad() {
		return idTipoEnfermedad;
	}


	public void setidTipoEnfermedad(int idTipoEnfermedad) {
		this.idTipoEnfermedad = idTipoEnfermedad;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
