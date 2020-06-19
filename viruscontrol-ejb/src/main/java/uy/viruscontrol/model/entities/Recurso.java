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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uy.viruscontrol.utils.DtEnfermedad;
import uy.viruscontrol.utils.DtRecurso;




@Entity
@Table(name="recurso")
public class Recurso implements Serializable {
	private static final long serialVersionUID = 4911353386206429832L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nombre;
	@Column(name="codigo_periferico")
	private String codigoPeriferico;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="tipo_recurso")
	private TipoRecurso tipoRecurso;
	
	
	
	@OneToMany(fetch = FetchType.EAGER,
			mappedBy = "recurso",
	        orphanRemoval = true
	    )
	private List<RecursoEnfermedad> enfermedades = new ArrayList<>();;
	
	
	public Recurso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recurso(String nombre, List<RecursoEnfermedad> enfermedades,TipoRecurso tipoRecurso) {
		super();
		
		this.nombre = nombre;
		this.enfermedades = enfermedades;
		this.tipoRecurso= tipoRecurso;
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

	public List<RecursoEnfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(List<RecursoEnfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}
	
	

	public TipoRecurso getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(TipoRecurso tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}
	
	
	

	public String getCodigoPeriferico() {
		return codigoPeriferico;
	}

	public void setCodigoPeriferico(String codigoPeriferico) {
		this.codigoPeriferico = codigoPeriferico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoPeriferico == null) ? 0 : codigoPeriferico.hashCode());
		result = prime * result + ((enfermedades == null) ? 0 : enfermedades.hashCode());
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((tipoRecurso == null) ? 0 : tipoRecurso.hashCode());
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
		Recurso other = (Recurso) obj;
		if (codigoPeriferico == null) {
			if (other.codigoPeriferico != null)
				return false;
		} else if (!codigoPeriferico.equals(other.codigoPeriferico))
			return false;
		if (enfermedades == null) {
			if (other.enfermedades != null)
				return false;
		} else if (!enfermedades.equals(other.enfermedades))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tipoRecurso == null) {
			if (other.tipoRecurso != null)
				return false;
		} else if (!tipoRecurso.equals(other.tipoRecurso))
			return false;
		return true;
	}

	public void asociarEnfermedad(Enfermedad enfermedad) {
		
		RecursoEnfermedad recursoEnfermedad = new RecursoEnfermedad(this, enfermedad);
		enfermedades.add(recursoEnfermedad);
		enfermedad.getRecursos().add(recursoEnfermedad);
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
	public List<DtEnfermedad> getDtEnfermedades() {
		List<DtEnfermedad> list = new ArrayList<DtEnfermedad>();
		for (RecursoEnfermedad it : this.enfermedades) {
			list.add(new DtEnfermedad(it.getEnfermedad().getId(), it.getEnfermedad().getNombre()));
		}
		return list;
	}
	
	public DtRecurso getDt() {
		return new DtRecurso(this.id, this.nombre, this.tipoRecurso, this.getDtEnfermedades(), 0);
	}
/*
	public void desasociarEnfermedad(Enfermedad enfermedad) {
		for (Iterator<RecursoEnfermedad> iterator = enfermedades.iterator();
	             iterator.hasNext(); ) {
			RecursoEnfermedad recursoEnfermedad = iterator.next();
	 
	            if (recursoEnfermedad.getRecurso().equals(this) &&
	            		recursoEnfermedad.getEnfermedad().equals(enfermedad)) {
	                iterator.remove();
	                recursoEnfermedad.getEnfermedad().getRecursos().remove(recursoEnfermedad);
	                recursoEnfermedad.setRecurso(null);
	                recursoEnfermedad.setEnfermedad(null);
	            }
	        }
	}
*/


	
	
	
}
