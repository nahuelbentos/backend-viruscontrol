package com.examen.beans;

import java.util.List;

import javax.ejb.Local;

import com.examen.entities.EstadoExamen;
import com.examen.entities.Examen;
import com.examen.entities.ProveedorExamen;

@Local
public interface PerifericoProveedorExamenLocal {
	public ProveedorExamen find(int idEnfermedad);
	public List<ProveedorExamen> findAllByEnfermedad(int idEnfermedad);
	public List<Examen> listaExamenesParaEnfermedad(int idEnfermedad);
	public Examen solicitarAltaExamen(int idPaciente, int idExamen, int idMedico);
	public EstadoExamen resultadoExamen(int idCaso);
}
