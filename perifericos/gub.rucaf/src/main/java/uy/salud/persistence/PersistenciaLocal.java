package uy.salud.persistence;

import java.util.List;

import javax.ejb.Local;

import uy.salud.entities.PrestadoraSalud;
import uy.salud.entities.Usuario;

@Local
public interface PersistenciaLocal {
	// operaciones sobre Prestadoras de Salud
	public List<PrestadoraSalud> getPrestadoras();
	public PrestadoraSalud findPrestadora(int id);
	public boolean addPrestadora(PrestadoraSalud prestadora);
	
	// operaciones sobre los usuarios de las prestadoras de salud
	public List<Usuario> getUsuarios();
	public Usuario findUsuario(int documento);
	public boolean addUsuario(Usuario usuario);
	public boolean updateUsuario(Usuario usuario);
	public boolean deleteUsuario(int documento);
}
