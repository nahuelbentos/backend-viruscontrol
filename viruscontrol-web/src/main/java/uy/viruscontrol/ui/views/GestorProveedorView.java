package uy.viruscontrol.ui.views;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import uy.viruscontrol.controllers.ProveedorBeanController;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.model.entities.ProveedorRecursos;

@Named("GestorProveedorView")
@RequestScoped
public class GestorProveedorView {

	String mensaje;
	
	//Datos Alta
	int tipo;
	private String nombreProveedor;
	private String direccion;
	private String barrio;
	private String rangoHorario;
	
	private ProveedorRecursos proveedorRecurso;
	
	private List<ProveedorRecursos> proveedoresRecursos;
	private List<ProveedorExamen> proveedoresExamenes;
	
	
	@PostConstruct
	public void init() {
		proveedoresRecursos = new ArrayList<ProveedorRecursos>();
		proveedoresRecursos = ProveedorBeanController.obtenerProveedoresRecursos();
		
		proveedoresExamenes = new ArrayList<ProveedorExamen>();
		proveedoresExamenes = ProveedorBeanController.obtenerProveedoresExamenes();
	}
	
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
	
	
	


	public List<ProveedorRecursos> getProveedoresRecursos() {
		return proveedoresRecursos;
	}

	public void setProveedoresRecursos(List<ProveedorRecursos> proveedoresRecursos) {
		this.proveedoresRecursos = proveedoresRecursos;
	}

	public List<ProveedorExamen> getProveedoresExamenes() {
		return proveedoresExamenes;
	}

	public void setProveedoresExamenes(List<ProveedorExamen> proveedoresExamenes) {
		this.proveedoresExamenes = proveedoresExamenes;
	}

	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public void agregarProveedor() {
		
		
	///ALTA	
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
	
	
	//ACTUALIZAR P RECURSOS
	//ACTUALIZAR - METODO ACTUALIZAR AJAX EVENT DATATABLE
		public void actualizarPR(RowEditEvent event) {
			
			proveedorRecurso = (ProveedorRecursos) event.getObject();
			boolean ok = ProveedorBeanController.actualizarProveedorRecurso(proveedorRecurso);
			
			if (ok) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Proveedor de Recursos actualizado"));
				
			}
			else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error! Proveedor no actualizado, verifique"));
				
			}
		}
		
		//ACTUALIZAR - METODO CANCELAR AJAX EVENT DATATABLE
		public void cancelar(RowEditEvent event) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Cancelado!"));
		}
		
		
	
	
}
