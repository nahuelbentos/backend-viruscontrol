package com.prestador.beans;

import java.util.List;

import javax.ejb.Local;

import com.prestador.entities.Medico;
import com.prestador.entities.PrestadoraSalud;

@Local
public interface PerifericoPrestadoraSaludLocal {
	public List<Medico> obtenerMedicos(int idPrestadora);
	public boolean estaDisponible(int idPrestadora);
	public int obtenerMedicoAsignado(int idPrestadora);
	public PrestadoraSalud obtenerPrestadorDeSalud(int idUsuario);
	public PrestadoraSalud obtenerPrestadorDeSaludAlternativo(int idUsuario);
}
