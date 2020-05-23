package uy.viruscontrol.bussines.serviceagents;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.PrestadoraSalud;

@Local
public interface ServiceAgentRucafLocal {
	public List<Medico> obtenerMedicos(int idPrestadora);
	public boolean estaDisponible(int idPrestadora);
	public int obtenerMedicoAsignado(int idPrestadora);
	public PrestadoraSalud obtenerPrestadorDeSalud(int idUsuario);
	public PrestadoraSalud obtenerPrestadorDeSaludAlternativo(int idUsuario);
}
