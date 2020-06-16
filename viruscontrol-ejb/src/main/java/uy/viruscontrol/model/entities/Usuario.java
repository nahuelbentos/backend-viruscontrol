package uy.viruscontrol.model.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@DiscriminatorColumn(name="tipo_usuario")
public abstract class Usuario implements Serializable{
	private static final long serialVersionUID = 3827070902901902553L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idUsuario;
	
	private String nombre;
	private String apellido;
	private String direccion;
	@Column(name="\"fecha_nacimiento\"")
	private Calendar fechaNacimiento;
	private String nacionalidad;
	private String correo;
	
	private boolean deleted=false;
	
	private String documento;
	@Column(name="\"nro_telefono\"")
	private String nroTelefono;
	
	/** atributos del negocio VirusControl **/
	private String username;
	private String password;
	@Column(name="\"primer_ingreso\"")
	private boolean primerIngreso;
	
	public Usuario() {
		super();
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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
	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Calendar fechaNacimiento) {
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
	public boolean isPrimerIngreso() {
		return primerIngreso;
	}
	public void setPrimerIngreso(boolean primerIngreso) {
		this.primerIngreso = primerIngreso;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNroTelefono() {
		return nroTelefono;
	}
	public void setNroTelefono(String nroTelefono) {
		this.nroTelefono = nroTelefono;
	}
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public Usuario(int idUsuario, String nombre, String apellido, String direccion, Calendar fechaNacimiento,
			String nacionalidad, String correo, String username, String password, boolean primerIngreso) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.correo = correo;
		this.username = username;
		this.password = password;
		this.primerIngreso = primerIngreso;
	}
	public Usuario(String nombre, String apellido, String correo, String username) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.username = username;
	}
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion="
				+ direccion + ", fechaNacimiento=" + fechaNacimiento + ", nacionalidad=" + nacionalidad + ", correo="
				+ correo + ", deleted=" + deleted + ", documento=" + documento + ", nroTelefono=" + nroTelefono
				+ ", username=" + username + ", password=" + password + ", primerIngreso=" + primerIngreso + "]";
	}
	
	
	
}
