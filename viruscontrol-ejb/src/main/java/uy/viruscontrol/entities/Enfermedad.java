package uy.viruscontrol.entities;

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
public class Enfermedad {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idEnfermedad;
	private String nombre;
	private boolean aprobada;
	@Column(name="\"nombre_agente\"")
	private String nombreAgente;
	
	@ManyToOne
	@JoinColumn(name="\"tipoenfermedad\"")
	private TipoEnfermedad tipoEnfermedad;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "enfermedad_sintoma", 
             joinColumns = { @JoinColumn(name = "idEnfermedad") }, 
             inverseJoinColumns = { @JoinColumn(name = "idSintoma") })
	private List<Sintoma> sintomas;


	public Enfermedad() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Enfermedad(int idEnfermedad, String nombre, boolean aprobada, String nombreAgente,
			TipoEnfermedad tipoEnfermedad, List<Sintoma> sintomas) {
		super();
		this.idEnfermedad = idEnfermedad;
		this.nombre = nombre;
		this.aprobada = aprobada;
		this.nombreAgente = nombreAgente;
		this.tipoEnfermedad = tipoEnfermedad;
		this.sintomas = sintomas;
	}


	public int getIdEnfermedad() {
		return idEnfermedad;
	}


	public void setIdEnfermedad(int idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
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
	
	
}
