package uy.viruscontrol.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("EXAMEN")
public class ProveedorExamen extends Proveedor implements Serializable {
	private static final long serialVersionUID = 2352742454735051987L;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "proveedorexamen_examen",
    joinColumns = {
	        @JoinColumn(
	            name = "id_proveedor",
	            referencedColumnName = "id"
	        )
	    },
	    inverseJoinColumns = {
	        @JoinColumn(
	            name = "id_examen",
	            referencedColumnName = "id"
	        )
	    }
	)
	private List<Examen> examenes;
	
	public ProveedorExamen() {
		super();
	}

	public ProveedorExamen(int id, String nombre,String direccion,String barrio,String rangoHorario) {
		super(id, nombre, direccion, barrio, rangoHorario);
	}

	public List<Examen> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<Examen> examenes) {
		this.examenes = examenes;
	}

}
