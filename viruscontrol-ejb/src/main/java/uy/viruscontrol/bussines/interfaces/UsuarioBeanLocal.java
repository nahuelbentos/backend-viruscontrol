package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.model.entities.Usuario;
import uy.viruscontrol.utils.VisitaPendiente;

@Local
public interface UsuarioBeanLocal {

	public boolean autenticarUsuario(String username, String password);
	
	public Usuario getUsuario(String username);
	
	public boolean registrarPrimerIngreso(Usuario user, TipoUsuario tipoUser);
	
	public void actualizarDatos(Usuario user);
	
	public List<VisitaPendiente> getVisitaPendiente(String username);
	
}
