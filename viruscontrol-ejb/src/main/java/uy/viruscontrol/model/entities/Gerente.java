package uy.viruscontrol.model.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("gerente")
public class Gerente extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	public Gerente() {
		super();
	}

	public Gerente(int idUsuario, String nombre, String apellido, String direccion, Calendar fechaNacimiento,
			String nacionalidad, String correo, String username, String password, boolean primerIngreso) {
		super(idUsuario, nombre, apellido, direccion, fechaNacimiento, nacionalidad, correo, username, password, primerIngreso);
		// TODO Auto-generated constructor stub
	}
	
}
