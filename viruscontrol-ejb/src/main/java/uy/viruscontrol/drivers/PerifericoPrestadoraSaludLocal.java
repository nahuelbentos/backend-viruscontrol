package uy.viruscontrol.drivers;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.PrestadoraSalud;

@Local
public interface PerifericoPrestadoraSaludLocal {
	public List<Medico> obtenerMedicos(int idPrestadora);
	public boolean estaDisponible(int idPrestadora);
	public int obtenerMedicoAsignado(int idPrestadora);
	public PrestadoraSalud obtenerPrestadorDeSalud(int idUsuario);
}
