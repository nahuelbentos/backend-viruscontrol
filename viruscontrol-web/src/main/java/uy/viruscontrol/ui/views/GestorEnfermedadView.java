package uy.viruscontrol.ui.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import uy.viruscontrol.bussines.interfaces.EnfermedadBeanLocal;
import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.entities.Sintoma;


@Named("GestorEnfermedadView")
@RequestScoped
public class GestorEnfermedadView implements Serializable{
	
	private static final long serialVersionUID = -1546397945313372054L;

	@Inject private EnfermedadBeanLocal enfermedadEjb;
	
	@Inject	private UserManager userManager;
	// Datos para la vista
	
	// Datos del negocio para alta enfermedad
	private String nombreEnfermedad;
	private String nombreTipoEnfermedad;
	private String nombreAgente;
	private List<Sintoma> sintomas;
	private String sintomasStr;
	private Float distanciaContagio=Float.valueOf(0);
	
	//Datos para listar enfermedades
	private List<Enfermedad>enfermedades;
	private Enfermedad enfermedad = new Enfermedad();
	
	//Datos Enfermedades No Aprobadas
	private List<String> enfermedadesNoAprobadas;
	private String nombreEnfermedadNoAprobada;
	
	private int seleccionada;
	
	public GestorEnfermedadView() {
		super();
	}
	
	@PostConstruct
	public void init() {
		enfermedadesNoAprobadas=new ArrayList<String>();
		for(Enfermedad enfermedad : enfermedadEjb.obtenerListaEnfermedadesNoAprobadas()) {
			enfermedadesNoAprobadas.add(enfermedad.getNombre());
		}
		
		enfermedades = new ArrayList<Enfermedad>();
		enfermedades = enfermedadEjb.obtenerEnfermedades();
	
	}
	
	
	
	
	public List<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(List<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public List<String> getEnfermedadesNoAprobadas() {
		return enfermedadesNoAprobadas;
	}
	
	public void setEnfermedadesNoAprobadas(List<String> enfermedadesNoAprobadas) {
		this.enfermedadesNoAprobadas = enfermedadesNoAprobadas;
	}
	
	public String getNombreEnfermedadNoAprobada() {
		return nombreEnfermedadNoAprobada;
	}
	
	public void setNombreEnfermedadNoAprobada(String nombreEnfermedadNoAprobada) {
		this.nombreEnfermedadNoAprobada = nombreEnfermedadNoAprobada;
	}

	public UserManager getUserManager() {
		return userManager;
	}
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	public String getNombreEnfermedad() {
		return nombreEnfermedad;
	}
	public void setNombreEnfermedad(String nombreEnfermedad) {
		this.nombreEnfermedad = nombreEnfermedad;
	}
	public String getNombreTipoEnfermedad() {
		return nombreTipoEnfermedad;
	}
	public void setNombreTipoEnfermedad(String nombreTipoEnfermedad) {
		this.nombreTipoEnfermedad = nombreTipoEnfermedad;
	}
	public String getNombreAgente() {
		return nombreAgente;
	}
	public void setNombreAgente(String nombreAgente) {
		this.nombreAgente = nombreAgente;
	}
	
	public String getSintomasStr() {
		return sintomasStr;
	}
	public void setSintomasStr(String sintomasStr) {
		this.sintomasStr = sintomasStr;
	}
	
	public Float getDistanciaContagio() {
		return distanciaContagio;
	}

	public void setDistanciaContagio(Float distanciaContagio) {
		this.distanciaContagio = distanciaContagio;
	}
	
	public void agregarNuevaEnfermedad() {
		this.sintomas = new ArrayList<Sintoma>();
		String[] ss = this.sintomasStr.split(",");
		for(int i = 0; i < ss.length; i++) {
			Sintoma s = new Sintoma();
			s.setNombre(ss[i]);
			this.sintomas.add(s);
		}
		
		boolean ok = enfermedadEjb.crearEnfermedadInfecciosa(this.nombreEnfermedad, this.nombreTipoEnfermedad, this.nombreAgente, this.sintomas, false, this.distanciaContagio);
		if (ok) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Se agrego la solicitud de alta de la enfermedad " + this.nombreEnfermedad + ". Un administrador debe aprobarla para que quede dada de alta."));
		
		}else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error, no se pudo crear la enfermedad, verifique."));
		}
		
		this.cleanForm();
	}
	
	//CU Aprobar Enfermedad
	public void aprobarEnfermedad() {
		int idAux = enfermedadEjb.getIdEnfermedadByName(nombreEnfermedadNoAprobada);
		
		boolean ok = enfermedadEjb.aprobarEnfermedadInfecciosa(idAux);
		
		if(ok) {
			
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("La enfermedad "+ this.getNombreEnfermedadNoAprobada() + " fue aprobada."));
		}else {
			
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error, no se pudo aprobar la enfermedad, verifique."));
			
		}
		this.cleanForm();
	}
	
	//Rechazar Enfermedad
		public void rechazarEnfermedad() {
			int idAux = enfermedadEjb.getIdEnfermedadByName(nombreEnfermedadNoAprobada);
			
			boolean ok = enfermedadEjb.rechazarEnfermedadInfecciosa(idAux);
			
			if(ok) {
				
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("La enfermedad "+ this.getNombreEnfermedadNoAprobada() + " fue rechazada."));
			}else {
				
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error, no se pudo rechazar la enfermedad, verifique."));
			}
			
			this.cleanForm();
		}
	
	
	public Map<String,String> getOpciones(){
		TreeMap<String,String> opciones = new TreeMap<String,String>();
		
		if (userManager.getCurrentUser() != null) {
			if (userManager.getCurrentUser() instanceof Administrador) {
				//nothing to do
			} else {
				if (userManager.getCurrentUser() instanceof Gerente) {
					opciones.put("ABM Enfermedad Infecciosa", UserManager.getDirVirtual(userManager.getCurrentUser())+"altaEnfermedad.xhtml");
					opciones.put("Gestor de Recursos para Enfermedad", UserManager.getDirVirtual(userManager.getCurrentUser())+"gestorRecursos.xhtml");
					opciones.put("Asociar Recurso a Enfermedad", UserManager.getDirVirtual(userManager.getCurrentUser())+"asociarRecursoEnfermedad.xhtml");
					
				}
			}
		}
				
		return opciones;
	}
	
	public void asociarEnfermedad() {
		System.out.println("Se ha seleccionado la enfermedad: "+this.seleccionada);
	}

	public int getSeleccionada() {
		return seleccionada;
	}

	public void setSeleccionada(int seleccionada) {
		this.seleccionada = seleccionada;
	}
	
	public void cleanForm() {
		setNombreEnfermedadNoAprobada(null);
		setNombreEnfermedad(null);
		setNombreTipoEnfermedad(null);
		setNombreAgente(null);
		setSintomasStr(null);
		setDistanciaContagio(Float.valueOf(0));
	}
}
