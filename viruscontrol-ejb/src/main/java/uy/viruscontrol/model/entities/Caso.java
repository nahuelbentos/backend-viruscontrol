package uy.viruscontrol.model.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uy.viruscontrol.bussines.enumerated.TipoCaso;

@Entity
@Table(name = "caso")
public class Caso implements Serializable  {
	
	
	private static final long serialVersionUID = 3053641279148705403L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Enumerated
	private TipoCaso tipoCaso;
	
	
	@ManyToOne
	private Departamento departamento;
	
	@ManyToOne
	private Examen examen;
	
	@ManyToOne
	private Enfermedad enfermedad;
	
	@ManyToOne
	private Medico medico;
	
	@ManyToOne
	private Ciudadano ciudadano;
	
	@ManyToOne
	private ProveedorExamen proveedorExamen;

	public Caso() {
	}

	public Caso(int id, TipoCaso tipoCaso, Departamento departamento, Examen examen, Enfermedad enfermedad) {
		super();
		this.id = id;
		this.tipoCaso = tipoCaso;
		this.departamento = departamento;
		this.examen = examen;
		this.enfermedad = enfermedad;
	}

	
	
	public ProveedorExamen getProveedorExamen() {
		return proveedorExamen;
	}

	public void setProveedorExamen(ProveedorExamen proveedorExamen) {
		this.proveedorExamen = proveedorExamen;
	}

	public Ciudadano getCiudadano() {
		return ciudadano;
	}

	public void setCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoCaso getTipoCaso() {
		return tipoCaso;
	}

	public void setTipoCaso(TipoCaso tipoCaso) {
		this.tipoCaso = tipoCaso;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	
	
}
