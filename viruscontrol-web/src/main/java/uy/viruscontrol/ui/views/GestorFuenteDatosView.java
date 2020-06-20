package uy.viruscontrol.ui.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import uy.viruscontrol.bussines.interfaces.FuenteDatosBeanLocal;
import uy.viruscontrol.model.entities.FuenteDeDatos;

@Named("GestorFuenteDatosView")
@RequestScoped
public class GestorFuenteDatosView implements Serializable{
	
	private static final long serialVersionUID = 4495934608841731944L;

	@Inject private FuenteDatosBeanLocal fuenteDatosEjb;
	
	//Datos alta
	private String codigo;
	private String url;
	private List<FuenteDeDatos> fuentesDeDatos;
	
	private FuenteDeDatos fuente;
	
	//Datos Eliminar
	private List<FuenteDeDatos> fuentesDeDatosEliminadas = new ArrayList<FuenteDeDatos>();
	
	@PostConstruct
	public void init() {
		fuentesDeDatos = new ArrayList<FuenteDeDatos>();
		fuentesDeDatos = fuenteDatosEjb.obtenerFuenteDeDatos();
		
		
	}
	
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	public List<FuenteDeDatos> getFuentesDeDatos() {
		return fuentesDeDatos;
	}


	public void setFuentesDeDatos(List<FuenteDeDatos> fuentesDeDatos) {
		this.fuentesDeDatos = fuentesDeDatos;
	}


	public void agregarFuenteDatos() {
		
		boolean ok = fuenteDatosEjb.crearFuenteDatos(codigo, url);
		if (ok) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Fuente de Datos con codigo "+ this.codigo +" creada."));
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error!, Fuente de Datos no creada, verifique"));
		}
		this.cleanForm();
	}
	
	//ACTUALIZAR - METODO ACTUALIZAR AJAX EVENT DATATABLE
		public void actualizar(RowEditEvent event) {
			
			fuente = (FuenteDeDatos) event.getObject();
			
			boolean ok = fuenteDatosEjb.actualizarFuenteDatos(fuente);
			
			if (ok) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("La Fuente de Datos fue actualizada"));
				
			}
			else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error!, Fuente de Datos no actualizada, verifique"));
				
			}
		}
		
		//ACTUALIZAR - METODO CANCELAR AJAX EVENT DATATABLE
		public void cancelar(RowEditEvent event) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Cancelado!"));
		}
	
		//Metodo que elimina de la lista padre aquellos objetos seleccionados en la tabla
		public String eliminarFuentesDeDatos() {
			for(FuenteDeDatos fuenteDatos : fuentesDeDatos) {
				if(fuenteDatos.isDeleted()) {
					fuentesDeDatosEliminadas.add(fuenteDatos);
				}
			}
			if(!fuentesDeDatosEliminadas.isEmpty()) {
				fuentesDeDatos.removeAll(fuentesDeDatosEliminadas);
				for(FuenteDeDatos fd : fuentesDeDatosEliminadas) {
					fuenteDatosEjb.eliminarFuenteDeDatos(fd);
				}
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Fuente de Datos Eliminado."));
			}
			
			return "gestorFuenteDatos";
		}
		
		private void cleanForm() {
			setCodigo(null);
			setUrl(null);
		}
}
