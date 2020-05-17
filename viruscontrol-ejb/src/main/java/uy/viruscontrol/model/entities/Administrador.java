package uy.viruscontrol.model.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("administrador")
public class Administrador extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	public Administrador() {
		super();
	}

	public Administrador(int idUsuario, String nombre, String apellido, String direccion, Calendar fechaNacimiento,
			String nacionalidad, String correo, String username, String password, boolean primerIngreso) {
		super(idUsuario, nombre, apellido, direccion, fechaNacimiento, nacionalidad, correo, username, password, primerIngreso);
		// TODO Auto-generated constructor stub
	}
	
}
