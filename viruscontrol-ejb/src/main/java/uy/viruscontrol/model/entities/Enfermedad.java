package uy.viruscontrol.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "enfermedad")
public class Enfermedad implements Serializable{
	private static final long serialVersionUID = 1224835107241516313L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nombre;
	private boolean aprobada;
	@Column(name="\"nombre_agente\"")
	private String nombreAgente;
	
	@ManyToOne
	@JoinColumn(name="\"tipoenfermedad\"")
	private TipoEnfermedad tipoEnfermedad;
	
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "enfermedad_sintoma",
    joinColumns = {
	        @JoinColumn(
	            name = "id_Enfermedad",
	            referencedColumnName = "id"
	        )
	    },
	    inverseJoinColumns = {
	        @JoinColumn(
	            name = "id_Sintoma",
	            referencedColumnName = "id"
	        )
	    }
	)
	private List<Sintoma> sintomas;

	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "enfermedad_recurso",
    joinColumns = {
	        @JoinColumn(
	            name = "id_Enfermedad",
	            referencedColumnName = "id"
	        )
	    },
	    inverseJoinColumns = {
	        @JoinColumn(
	            name = "id_Recurso",
	            referencedColumnName = "id"
	        )
	    }
	)
	private List<Recurso> recursos;
	
	public Enfermedad() {
		super();
		// TODO Auto-generated constructor stub
	}


	


	public Enfermedad(int id, String nombre, boolean aprobada, String nombreAgente,
			TipoEnfermedad tipoEnfermedad, List<Sintoma> sintomas, List<Recurso> recursos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.aprobada = aprobada;
		this.nombreAgente = nombreAgente;
		this.tipoEnfermedad = tipoEnfermedad;
		this.sintomas = sintomas;
		this.recursos = recursos;
	}


	public int getIdEnfermedad() {
		return id;
	}


	public void setIdEnfermedad(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public boolean isAprobada() {
		return aprobada;
	}


	public void setAprobada(boolean aprobada) {
		this.aprobada = aprobada;
	}


	public String getNombreAgente() {
		return nombreAgente;
	}


	public void setNombreAgente(String nombreAgente) {
		this.nombreAgente = nombreAgente;
	}


	public TipoEnfermedad getTipoEnfermedad() {
		return tipoEnfermedad;
	}


	public void setTipoEnfermedad(TipoEnfermedad tipoEnfermedad) {
		this.tipoEnfermedad = tipoEnfermedad;
	}


	public List<Sintoma> getSintomas() {
		return sintomas;
	}


	public void setSintomas(List<Sintoma> sintomas) {
		this.sintomas = sintomas;
	}


	public List<Recurso> getRecursos() {
		return recursos;
	}


	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}
	
	
	public void agregarRecursoRecomendado(Recurso recurso) {
		
		this.recursos.add(recurso);
		recurso.getEnfermedades().add(this);
		
	}
	
}
