package uy.viruscontrol.model.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("ciudadano")
public class Ciudadano extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private PrestadoraSalud prestadoraSalud;
	
	@Column(name="token_push_notifications")
	private String tokenPushNotifications;
	
	public Ciudadano() {
		super();
	}

	public Ciudadano(int idUsuario, String nombre, String apellido, String direccion, Calendar fechaNacimiento,
			String nacionalidad, String correo, String username, String password, boolean primerIngreso) {
		super(idUsuario, nombre, apellido, direccion, fechaNacimiento, nacionalidad, correo, username, password, primerIngreso);
	}

	public PrestadoraSalud getPrestadoraSalud() {
		return prestadoraSalud;
	}

	public void setPrestadoraSalud(PrestadoraSalud prestadoraSalud) {
		this.prestadoraSalud = prestadoraSalud;
	}
	
	public String getTokenPushNotifications() {
		return tokenPushNotifications;
	}

	public void setTokenPushNotifications(String tokenPushNotifications) {
		this.tokenPushNotifications = tokenPushNotifications;
	}
	
}
