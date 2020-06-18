package uy.viruscontrol.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uy.viruscontrol.utils.DtEnfermedad;



@Entity
@Table(name = "enfermedad")
public class Enfermedad implements Serializable{
	private static final long serialVersionUID = 1224835107241516313L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nombre;
	private boolean aprobada=false;
	private boolean rechazada=false;
	@Column(name="\"nombre_agente\"")
	private String nombreAgente;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="\"tipo_enfermedad\"")
	private TipoEnfermedad tipoEnfermedad;
	
	
	@ManyToMany(cascade = CascadeType.MERGE)
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

	
	
	@OneToMany(fetch = FetchType.EAGER,
	        mappedBy = "enfermedad",
	        orphanRemoval = true
    )
    private List<RecursoEnfermedad> recursos = new ArrayList<>();
	
	@Column(name = "distancia_contagio")
	private float distanciaContagio;
	
	public Enfermedad() {
		super();
	}

	public Enfermedad(String nombre, boolean aprobada, String nombreAgente, TipoEnfermedad tipoEnfermedad,
			List<Sintoma> sintomas, boolean rechazada, float distanciaContagio) {
		super();
		this.nombre = nombre;
		this.aprobada = aprobada;
		this.nombreAgente = nombreAgente;
		this.tipoEnfermedad = tipoEnfermedad;
		this.sintomas = sintomas;
		this.rechazada = rechazada;
		this.distanciaContagio = distanciaContagio;
	}
	
	public float getDistanciaContagio() {
		return distanciaContagio;
	}

	public void setDistanciaContagio(float distanciaContagio) {
		this.distanciaContagio = distanciaContagio;
	}

	public boolean isRechazada() {
		return rechazada;
	}

	public void setRechazada(boolean rechazada) {
		this.rechazada = rechazada;
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

	public List<RecursoEnfermedad> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<RecursoEnfermedad> recursos) {
		this.recursos = recursos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (aprobada ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((nombreAgente == null) ? 0 : nombreAgente.hashCode());
		result = prime * result + (rechazada ? 1231 : 1237);
		result = prime * result + ((recursos == null) ? 0 : recursos.hashCode());
		result = prime * result + ((sintomas == null) ? 0 : sintomas.hashCode());
		result = prime * result + ((tipoEnfermedad == null) ? 0 : tipoEnfermedad.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enfermedad other = (Enfermedad) obj;
		if (aprobada != other.aprobada)
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (nombreAgente == null) {
			if (other.nombreAgente != null)
				return false;
		} else if (!nombreAgente.equals(other.nombreAgente))
			return false;
		if (rechazada != other.rechazada)
			return false;
		if (recursos == null) {
			if (other.recursos != null)
				return false;
		} else if (!recursos.equals(other.recursos))
			return false;
		if (sintomas == null) {
			if (other.sintomas != null)
				return false;
		} else if (!sintomas.equals(other.sintomas))
			return false;
		if (tipoEnfermedad == null) {
			if (other.tipoEnfermedad != null)
				return false;
		} else if (!tipoEnfermedad.equals(other.tipoEnfermedad))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.getId() + " - " + this.getNombre();
	}
	
	public DtEnfermedad getDt() {
		return new DtEnfermedad(this.id, this.nombre);
	}
}
