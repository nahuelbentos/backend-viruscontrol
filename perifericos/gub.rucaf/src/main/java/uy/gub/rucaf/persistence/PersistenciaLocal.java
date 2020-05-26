package uy.gub.rucaf.persistence;

import java.util.List;

import javax.ejb.Local;

import uy.gub.rucaf.entities.PrestadoraSalud;
import uy.gub.rucaf.entities.Usuario;

@Local
public interface PersistenciaLocal {
	public List<PrestadoraSalud> getPrestadoras();
	public boolean addPrestadora(PrestadoraSalud prestadora);
	public PrestadoraSalud findPrestadoraUsuario(int documento);
	public boolean addUsuario(Usuario usuario);
	public boolean updateUsuario(Usuario usuario);
	public boolean deleteUsuario(int documento);
}
