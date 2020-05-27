package uy.viruscontrol.model.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "visita_medico",  uniqueConstraints={
		   @UniqueConstraint(columnNames={"id_medico", "id_ciudadano", "fecha_asignacion"})
		})
public class VisitaMedico implements Serializable {
	private static final long serialVersionUID = 771155363015049522L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idVisitaMedico;
	
	@ManyToOne
	@JoinColumn(name = "id_medico")
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name = "id_ciudadano")
	private Ciudadano ciudadano;
	
	@Column(name = "fecha_asignacion")
	private Calendar fechaAsignacion;

	@Column(name = "visita_realizada")
	private boolean visitaRealizada;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "visitamedico_sintomas",
		joinColumns = {
			@JoinColumn(
					name = "id_visitamedico",
					referencedColumnName = "id"
					)
			},
		inverseJoinColumns = {
			@JoinColumn(
					name = "id_sintoma",
					referencedColumnName = "id"
					)
			}
		)
	private List<Sintoma> sintomas;
	
	public VisitaMedico() {
		super();
	}

	public VisitaMedico(Medico medico, Ciudadano ciudadano, Calendar fechaAsignacion, boolean visitaRealizada) {
		super();
		this.medico = medico;
		this.ciudadano = ciudadano;
		this.fechaAsignacion = fechaAsignacion;
		this.visitaRealizada = visitaRealizada;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Ciudadano getCiudadano() {
		return ciudadano;
	}

	public void setCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	}

	public Calendar getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Calendar fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public boolean isVisitaRealizada() {
		return visitaRealizada;
	}

	public void setVisitaRealizada(boolean visitaRealizada) {
		this.visitaRealizada = visitaRealizada;
	}
	
}
