package uy.viruscontrol.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="recurso")
public class Recurso implements Serializable {
	private static final long serialVersionUID = 4911353386206429832L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nombre;
	
	/*
	//@ManyToMany(mappedBy = "recursos", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@OneToMany(mappedBy = "recurso")
	private List<RecursoEnfermedad> recursosEnfermedades;
*/
	
	@Transient
	private List<Enfermedad> enfermedades;
	
	
	public Recurso() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	


	public Recurso(int id, String nombre, List<Enfermedad> enfermedades) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.enfermedades = enfermedades;
	}





	public int getId() {
		return id;
	}

	

	





	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(List<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}


	//Se deja comentado dado que estar actualizando en ambas entidades puede causar errores 
	//para evitar eso actualizo de un solo lado ambas entidades 
	//por eso en agregarRecursoRecomendado (en Enfermedad) hago tambien recurso.getEnfermedades().add(this);
	
	public void asociarEnfermedad(Enfermedad enfermedad) {
		
		this.enfermedades.add(enfermedad);
		
		
	}
	
	
	
}
