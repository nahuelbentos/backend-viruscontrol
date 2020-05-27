package uy.viruscontrol.bussines.serviceagents;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.PrestadoraSalud;

@Local
public interface ServiceAgentPrestadoraSaludLocal {
	// Consultas a Periférico Prestadoras de Salud
	public List<Medico> obtenerMedicos(int idPrestadora);
	public boolean estaDisponible(int idPrestadora);
	public int obtenerMedicoAsignado(int idPrestadora);
	public PrestadoraSalud obtenerPrestadorDeSaludAlternativo(int documento);
	
	// Consultas a Periférico Salud.uy / Rucaf
	public PrestadoraSalud obtenerPrestadorDeSalud(int documento);
}
