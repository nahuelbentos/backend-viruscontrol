package uy.viruscontrol.ui.views;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import uy.viruscontrol.controllers.ProveedorBeanController;

@Named("GestorProveedorView")
@RequestScoped
public class GestorProveedorView {

	String mensaje;
	int tipo;
	String nombreProveedor;
	String direccion;
	String barrio;
	String rangoHorario;
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
	
	public String getNombreProveedor() {
		return nombreProveedor;
	}
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getRangoHorario() {
		return rangoHorario;
	}
	public void setRangoHorario(String rangoHorario) {
		this.rangoHorario = rangoHorario;
	}
	
	
	


	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public void agregarProveedor() {
		
		
		
		boolean ok = ProveedorBeanController.crearProveedor(tipo, nombreProveedor, direccion, barrio, rangoHorario);
		if (ok) {
			if (tipo==1) {
				this.mensaje = "El Proveedor de Recurso " + this.getNombreProveedor() + " se creó con éxito";
			}else {
				this.mensaje = "El Proveedor de Examen " + this.getNombreProveedor() + " se creó con éxito";
			}
		}
		else {
			this.mensaje = "Error Proveedor no creado, verifique";
		}
	}
}
