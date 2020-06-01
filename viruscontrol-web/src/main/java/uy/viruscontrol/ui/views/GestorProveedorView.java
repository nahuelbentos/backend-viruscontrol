package uy.viruscontrol.ui.views;

import java.io.Serializable;
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
public class GestorProveedorView  implements Serializable{

	
	private static final long serialVersionUID = 1429289222086985379L;
	
	//Datos Alta
	int tipo;
	private String nombreProveedor;
	private String direccion;
	private String barrio;
	private String rangoHorario;
	private String codigoPeriferico;
	
	private ProveedorRecursos proveedorRecurso;
	private ProveedorExamen proveedorExamen;
	
	private List<ProveedorRecursos> proveedoresRecursos;
	private List<ProveedorExamen> proveedoresExamenes;
	private List<ProveedorRecursos> proveedoresRecursosPeriferico;
	
	//Eliminar
	private List<ProveedorRecursos> proveedoresRecursosEliminados = new ArrayList<>();
	private List<ProveedorExamen> proveedoresExamenesEliminados = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		proveedoresRecursos = new ArrayList<ProveedorRecursos>();
		proveedoresRecursos = ProveedorBeanController.obtenerProveedoresRecursos();
		
		proveedoresExamenes = new ArrayList<ProveedorExamen>();
		proveedoresExamenes = ProveedorBeanController.obtenerProveedoresExamenes();
		
		proveedoresRecursosPeriferico = new ArrayList<ProveedorRecursos>();
		proveedoresRecursosPeriferico = ProveedorBeanController.obtenerProveedoresPeriferico();
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

	
	public ProveedorRecursos getProveedorRecurso() {
		return proveedorRecurso;
	}

	public void setProveedorRecurso(ProveedorRecursos proveedorRecurso) {
		this.proveedorRecurso = proveedorRecurso;
	}

	public ProveedorExamen getProveedorExamen() {
		return proveedorExamen;
	}

	public void setProveedorExamen(ProveedorExamen proveedorExamen) {
		this.proveedorExamen = proveedorExamen;
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
	
	
	public String getCodigoPeriferico() {
		return codigoPeriferico;
	}

	public void setCodigoPeriferico(String codigoPeriferico) {
		this.codigoPeriferico = codigoPeriferico;
	}

	public List<ProveedorRecursos> getProveedoresRecursosPeriferico() {
		return proveedoresRecursosPeriferico;
	}

	public void setProveedoresRecursosPeriferico(List<ProveedorRecursos> proveedoresRecursosPeriferico) {
		this.proveedoresRecursosPeriferico = proveedoresRecursosPeriferico;
	}

	public void agregarProveedor() {
		
		
	///ALTA	
		boolean ok = ProveedorBeanController.crearProveedor(tipo, nombreProveedor, direccion, barrio, rangoHorario, codigoPeriferico);
		if (ok) {
			if (tipo==1) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Proveedor de Recurso "+this.nombreProveedor+" creado."));
			}else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Proveedor de Exámen "+this.nombreProveedor+" creado."));
			}
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error Proveedor no creado, verifique"));
		}
		
		this.cleanForm();
	}
	
	
	//ACTUALIZAR P RECURSOS
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
		
		//ACTUALIZAR P Examenes
		public void actualizarPE(RowEditEvent event) {
					
			proveedorExamen = (ProveedorExamen) event.getObject();
			boolean ok = ProveedorBeanController.actualizarProveedorExamen(proveedorExamen);
			
			if (ok) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Proveedor de Examenes actualizado"));
					
			}
			else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error! Proveedor no actualizado, verifique"));
				
			}
		}
				
		//Metodo que elimina de la lista padre aquellos objetos seleccionados en la tabla
		public String eliminarProveedorRecursos() {
			for(ProveedorRecursos pr : proveedoresRecursos) {
				if(pr.isDeleted()) {
					proveedoresRecursosEliminados.add(pr);
				}
			}
			if(!proveedoresRecursosEliminados.isEmpty()) {
				proveedoresRecursos.removeAll(proveedoresRecursosEliminados);
				for(ProveedorRecursos pre : proveedoresRecursosEliminados) {
					ProveedorBeanController.eliminarProveedorRecursos(pre);
				}
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Proveedor Eliminado."));
			}
			
			
			return "gestorProveedores";
		}
		
		
		//Metodo que elimina de la lista padre aquellos objetos seleccionados en la tabla
				public String eliminarProveedorExamenes() {
					for(ProveedorExamen pr : proveedoresExamenes) {
						if(pr.isDeleted()) {
							proveedoresExamenesEliminados.add(pr);
						}
					}
					if(!proveedoresExamenesEliminados.isEmpty()) {
						proveedoresExamenes.removeAll(proveedoresExamenesEliminados);
						for(ProveedorExamen pre : proveedoresExamenesEliminados) {
							ProveedorBeanController.eliminarProveedorExamenes(pre);
						}
						FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Proveedor Eliminado."));
					}
					
					
					return "gestorProveedores";
				}
	
				public void cleanForm() {
					
					setTipo(0);
					setNombreProveedor(null);
					setDireccion(null);
					setBarrio(null);
					setRangoHorario(null);
					setCodigoPeriferico(null);
					
				}
}
