package uy.viruscontrol.bussines.interfaces;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;

import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.Usuario;

@Remote
public interface UsuarioBeanRemote {
	
	public boolean registrarUsuario(Usuario user);
	
	List<Ciudadano> mostrarCiudadanos();
	
	List<Medico> mostrarMedicos();
	
	List<Gerente> mostrarGerentes();
	
	List<Administrador> mostrarAdministradores();
	
	void editarCiudadano(int ciudadanoId, String nombre, String apellido, String correo, String direccion,
			String nacionalidad, String userName, Calendar fecha);
	
}
