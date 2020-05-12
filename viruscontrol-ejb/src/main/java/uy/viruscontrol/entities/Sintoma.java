package uy.viruscontrol.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sintoma")
public class Sintoma {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSintoma;
	private String nombre;
	
	
	
	public Sintoma() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Sintoma(int idSintoma, String nombre) {
		super();
		this.idSintoma = idSintoma;
		this.nombre = nombre;
	}
	
	public int getidSintoma() {
		return idSintoma;
	}
	
	public void setId(int idSintoma) {
		this.idSintoma = idSintoma;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
