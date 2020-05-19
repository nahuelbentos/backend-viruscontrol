package uy.viruscontrol.drivers;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.EstadoExamen;
import uy.viruscontrol.model.entities.Examen;
import uy.viruscontrol.model.entities.ProveedorExamen;

@Local
public interface PerifericoProveedorExamenLocal {
	public ProveedorExamen find(int idEnfermedad);
	public List<Examen> listaExamenesParaEnfermedad(int idEnfermedad);
	public Examen solicitarAltaExamen(int idPaciente, int idExamen, int idMedico);
	public EstadoExamen resultadoExamen(int idCaso);
}
