package uy.viruscontrol.ui.views;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import uy.viruscontrol.controllers.EnfermedadBeanController;

@Named("GestorAltaRecursoRecomendadoView")
@RequestScoped
public class GestorRecursoRecomendadoView {

	//Datos del negocio para el alta de recurso recomendado
		private String nombre_Enfermedad;
		private String nombreRecurso; 
		private boolean recursoTrata;
		private boolean recursoPreviene;
		
		private String mensaje;
		
		
		public String getMensaje() {
			return mensaje;
		}
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		
		public String getNombre_Enfermedad() {
			return nombre_Enfermedad;
		}
		public void setNombre_Enfermedad(String nombre_Enfermedad) {
			this.nombre_Enfermedad = nombre_Enfermedad;
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
		
		
		
		public void agregarRecursoRecomendadoEnfermedad() {
			
			int ok = EnfermedadBeanController.altaRecursoRecomendado(nombre_Enfermedad, nombreRecurso, recursoTrata, recursoPreviene);
			
			if (ok==1) {
				this.mensaje = "Recurso "+ this.getNombreRecurso() + " creado.";
				}
			if(ok==2) {
					this.mensaje = "Recurso "+ this.getNombreRecurso() + " asociado a la Enfermedad "+this.getNombre_Enfermedad();
				}
			if(ok==3) {
						this.mensaje = "Recurso Ok. La enfermedad a asociar no existe, debe crearla.";
					}
			if(ok==0) {
				this.mensaje = "Error, El Recurso ya se encuentra asociado a la Enfermedad.";
			}
					
					
		}
}
