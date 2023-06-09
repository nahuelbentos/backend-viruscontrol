package uy.viruscontrol.model.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
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

	@Column(name="fecha_sospechoso")
	private Calendar fechaSospechoso;

	@Column(name="fecha_confirmado")
	private Calendar fechaConfirmado;
	
	@Column(name="notificacion_enviada", columnDefinition="boolean default false", nullable=false)
	private boolean notificacionEnviada;
	
	@Column(name="pdf_examen")
	private String pathPdfResultadoExamen;
	
	public String getPathPdfResultadoExamen() {
		return pathPdfResultadoExamen;
	}

	public void setPathPdfResultadoExamen(String pathPdfResultadoExamen) {
		this.pathPdfResultadoExamen = pathPdfResultadoExamen;
	}

	public Caso() {
		super();
	}

	public Caso(int id, TipoCaso tipoCaso, Departamento departamento, Examen examen, Enfermedad enfermedad, boolean notificacionEnviada) {
		super();
		this.id = id;
		this.tipoCaso = tipoCaso;
		this.departamento = departamento;
		this.examen = examen;
		this.enfermedad = enfermedad;
		this.notificacionEnviada = notificacionEnviada;
	}
	
	public Calendar getFechaConfirmado() {
		return fechaConfirmado;
	}

	public void setFechaConfirmado(Calendar fechaConfirmado) {
		this.fechaConfirmado = fechaConfirmado;
	}

	public Calendar getFechaSospechoso() {
		return fechaSospechoso;
	}

	public void setFechaSospechoso(Calendar fechaSospechoso) {
		this.fechaSospechoso = fechaSospechoso;
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
	
	public boolean isNotificacionEnviada() {
		return notificacionEnviada;
	}

	public void setNotificacionEnviada(boolean notificacionEnviada) {
		this.notificacionEnviada = notificacionEnviada;
	}
	
}
