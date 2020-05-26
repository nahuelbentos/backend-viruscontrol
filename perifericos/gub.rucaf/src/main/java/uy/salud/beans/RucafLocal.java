package uy.salud.beans;

import java.util.List;

import javax.ejb.Local;

import uy.salud.entities.PrestadoraSalud;

@Local
public interface RucafLocal {
	public List<PrestadoraSalud> obtenerPrestadoras();
	public PrestadoraSalud obtenerPrestadoraUsuario(int documento);
}
