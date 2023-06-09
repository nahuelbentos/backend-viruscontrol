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

import uy.viruscontrol.bussines.interfaces.EnfermedadBeanLocal;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Recurso;

@Named("GestorRecursoRecomendadoView")
@RequestScoped
public class GestorRecursoRecomendadoView implements Serializable{

	private static final long serialVersionUID = 644369552012190591L;


	@Inject private EnfermedadBeanLocal enfermedadEjb;
	
	
	//Datos del negocio para el alta de recurso recomendado
		
	
		private List<String>enfermedades;
		private String nombreEnfermedad;
		
		private List<String>recursos;
		private String nombreRecurso; 
		
		private boolean recursoTrata;
		private boolean recursoPreviene;
		
		
		public GestorRecursoRecomendadoView() {
			super();
		}
		
		
		@PostConstruct
		public void init() {
			enfermedades=new ArrayList<String>();
			recursos=new ArrayList<String>();
				
			for(Enfermedad enfermedad : enfermedadEjb.obtenerEnfermedades()) {
					enfermedades.add(enfermedad.getNombre());
			}
			
			for(Recurso recurso : enfermedadEjb.obtenerRecursos()) {
				recursos.add(recurso.getNombre());
			}
		
		}
		
		
		public String getNombreEnfermedad() {
			return nombreEnfermedad;
		}
		public void setNombreEnfermedad(String nombreEnfermedad) {
			this.nombreEnfermedad = nombreEnfermedad;
		}
		public String getNombreRecurso() {
			return nombreRecurso;
		}
		public void setNombreRecurso(String nombreRecurso) {
			this.nombreRecurso = nombreRecurso;
		}
		public boolean isRecursoTrata() {
			return recursoTrata;
		}
		public void setRecursoTrata(boolean recursoTrata) {
			this.recursoTrata = recursoTrata;
		}
		public boolean isRecursoPreviene() {
			return recursoPreviene;
		}
		public void setRecursoPreviene(boolean recursoPreviene) {
			this.recursoPreviene = recursoPreviene;
		}
		
		
		
		
		public List<String> getEnfermedades() {
			return enfermedades;
		}


		public void setEnfermedades(List<String> enfermedades) {
			this.enfermedades = enfermedades;
		}


		public List<String> getRecursos() {
			return recursos;
		}


		public void setRecursos(List<String> recursos) {
			this.recursos = recursos;
		}


		public void asociarRecursoRecomendadoEnfermedad() {
			
			boolean ok = enfermedadEjb.asociarRecursoRecomendado(nombreEnfermedad, nombreRecurso, recursoTrata, recursoPreviene);
			
			if (ok) {
				
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("El Recurso fue asociado a la Enfermedad."));
			}
			else {
				
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("El Recurso ya se encuentra asociadoa la Enfermedad."));
			}
				
			this.cleanForm();
		}
		
		public void cleanForm() {
			setNombreRecurso(null);
			setNombreEnfermedad(null);
			setRecursoPreviene(false);
			setRecursoTrata(false);
		}
}
