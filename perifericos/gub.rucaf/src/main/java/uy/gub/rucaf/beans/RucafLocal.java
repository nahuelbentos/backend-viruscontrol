package uy.gub.rucaf.beans;

import java.util.List;

import javax.ejb.Local;

import uy.gub.rucaf.entities.PrestadoraSalud;

@Local
public interface RucafLocal {
	public List<PrestadoraSalud> obtenerPrestadoras();
	public PrestadoraSalud obtenerPrestadoraUsuario();
}
