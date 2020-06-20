package uy.viruscontrol.ui.views;

import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.bussines.interfaces.UsuarioBeanLocal;
import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.entities.Usuario;

@Named("RegisterView")
@RequestScoped
public class RegisterView {
	
	private String nombre;
	private String apellido;
	private String direccion;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String correo;
	
	private String username;
	private String password;

	private TipoUsuario tipoUsuario;

	@Inject private UsuarioBeanLocal usuarioEjb; 
	
	public RegisterView() {
		super();
	}
	
	public void register() {
		Usuario user;
		boolean ok = false;
		
		switch (this.tipoUsuario) {
			case ADMINISTRADOR:
				user = new Administrador();
				break;
			case GERENTE:
				user = new Gerente();
				break;
			default:
				user = null;
				break;
		}
		
		user = setData(user);
		
		
		if (user != null)
			ok = usuarioEjb.registrarUsuario(user);
		
		System.out.println("registro usuario? " + ok);
	}
	
	private Usuario setData(Usuario user) {
		
		Calendar nacimientoAsCalendar = Calendar.getInstance();
		nacimientoAsCalendar.setTime(this.fechaNacimiento);
		
		user.setNombre(this.nombre);
		user.setApellido(this.apellido);
		user.setCorreo(this.correo);
		user.setFechaNacimiento(nacimientoAsCalendar);
		user.setDireccion(this.direccion);
		user.setNacionalidad(this.nacionalidad);
		user.setPassword(this.password);
		user.setUsername(this.username);
		
		return user;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
}
