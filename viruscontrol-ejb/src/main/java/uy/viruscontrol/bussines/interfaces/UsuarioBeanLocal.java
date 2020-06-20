package uy.viruscontrol.bussines.interfaces;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.Usuario;

@Local
public interface UsuarioBeanLocal {

	public boolean autenticarUsuario(String username, String password);
	
	public Usuario getUsuario(String username);
	
	public boolean registrarPrimerIngreso(Usuario user, TipoUsuario tipoUser);
	
	public boolean registrarUsuario(Usuario user);
	
	public void actualizarDatos(Usuario user);

	List<Ciudadano> mostrarCiudadanos();

	List<Medico> mostrarMedicos();

	List<Gerente> mostrarGerentes();

	List<Administrador> mostrarAdministradores();

	void editarCiudadano(int ciudadanoId, String nombre, String apellido, String correo, String direccion,
			String nacionalidad, String userName, Calendar fecha);
	
	void editarMedico(int medicoId, String nombre, String apellido, String correo, String direccion,
			String nacionalidad, String userName, Calendar fecha);
	
	void editarGerente(int medicoId, String nombre, String apellido, String correo, String direccion,
			String nacionalidad, String userName, Calendar fecha, String password);
	
	void editarAdmin(int adminId, String nombre, String apellido, String correo, String direccion, String nacionalidad,
			String userName, Calendar fecha, String password);

	boolean eliminarUsuario(Usuario user);
}
