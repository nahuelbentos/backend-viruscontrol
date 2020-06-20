package uy.viruscontrol.ui.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import uy.viruscontrol.bussines.interfaces.EnfermedadBeanLocal;
import uy.viruscontrol.bussines.interfaces.FuenteDatosBeanLocal;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.FuenteDeDatos;
import uy.viruscontrol.model.entities.FuenteDeDatosEnfermedad;

@Named("GestorFuenteDatosEnfermedadView")
@RequestScoped
public class GestorFuenteDeDatosEnfermedadView implements Serializable{
	private static final long serialVersionUID = 3638166672750539462L;

	@Inject private EnfermedadBeanLocal enfermedadEjb;
	@Inject private FuenteDatosBeanLocal fuenteDatosEjb;
	
	@Inject private UserManager userManager;
	// Datos para la vista
	
	private List<Enfermedad> enfermedades;
	private int enfSeleccionada;
	private List<FuenteDeDatos> fuentes;
	private int fuenteSeleccionada;
	
	private List<FuenteDeDatosEnfermedad> fuentesEnfermedad;
	private String filtros;
	private String url;
	
	public GestorFuenteDeDatosEnfermedadView() {
		super();
	}
	public int getenfSeleccionada() {
		return enfSeleccionada;
	}

	public void setenfSeleccionada(int enfSeleccionada) {
		this.enfSeleccionada = enfSeleccionada;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public List<FuenteDeDatosEnfermedad> getFuentesEnfermedad() {
		return fuentesEnfermedad;
	}

	public void setFuentesEnfermedad(List<FuenteDeDatosEnfermedad> fuentesEnfermedad) {
		this.fuentesEnfermedad = fuentesEnfermedad;
	}
	
	public List<FuenteDeDatos> getFuentes() {
		return fuentes;
	}

	public void setFuentes(List<FuenteDeDatos> fuentes) {
		this.fuentes = fuentes;
	}

	public int getFuenteSeleccionada() {
		return fuenteSeleccionada;
	}

	public void setFuenteSeleccionada(int fuenteSeleccionada) {
		this.fuenteSeleccionada = fuenteSeleccionada;
	}

	public String getFiltros() {
		return filtros;
	}

	public void setFiltros(String filtros) {
		this.filtros = filtros;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@PostConstruct
	public void init() {
			enfermedades = new ArrayList<Enfermedad>();
			enfermedades = enfermedadEjb.obtenerEnfermedades();
			fuentes = fuenteDatosEjb.obtenerFuenteDeDatos();
			fuentesEnfermedad = fuenteDatosEjb.obtenerTodosFuenteDeDatosEnfermedad();
	}
	
	public List<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(List<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}
	
	public void asociarEnfermedad() {
//		System.out.println(getClass().getCanonicalName()+"] Se ha seleccionado la enfermedad: "+this.enfSeleccionada);
		
		Enfermedad enf = this.getEnfermedadFromList(enfSeleccionada);
		FuenteDeDatos fuente = this.getFuenteDeDatosFromList(fuenteSeleccionada);
		
		FuenteDeDatosEnfermedad f = new FuenteDeDatosEnfermedad();
		f.setEnfermedad(enf);
		f.setFuente(fuente);
		f.setFiltros(filtros);
		if (url == null)
			f.setUrl(fuente.getUrl());
		else
			f.setUrl(url);
		fuenteDatosEjb.crearFuenteParaEnfermedad(f);
		this.cleanForm();
	}

	public void refrescarFuentesPorEnfermedad() {
		Enfermedad enf = getEnfermedadFromList(enfSeleccionada);
		try {
			fuentesEnfermedad = fuenteDatosEjb.obtenerFuentesPorEnfermedad(enf);
//			for (FuenteDeDatosEnfermedad it : fuentesEnfermedad)
//				System.out.println("resultado: "+it.getId() + " " + it.getUrl());
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("["+getClass().getCanonicalName()+"] ERROR: No se pudo obtener el listado de fuentes de datos. Para mas detalles habilitar la traza");
		}
	}
	
	public void deleteListener(ActionEvent event) {
		int idEliminar = (Integer)event.getComponent().getAttributes().get("idEliminar");
		fuenteDatosEjb.eliminarFuenteDeDatosEnfermedad(idEliminar);
	}
	
	// FUNCIONES AUXILIARES
	private Enfermedad getEnfermedadFromList(int id) {
		Enfermedad enf = new Enfermedad();
		for (Enfermedad it : enfermedades) {
			if (it.getId() == id)
				enf = it;
		}
		return enf;
	}
	
	private FuenteDeDatos getFuenteDeDatosFromList(int id) {
		FuenteDeDatos fuente = new FuenteDeDatos();
		for (FuenteDeDatos it : fuentes) {
			if (it.getId() == id)
				fuente = it;
		}
		return fuente;
	}
	
	private void cleanForm() {
		setenfSeleccionada(0);
		setFuenteSeleccionada(0);
		setFiltros(null);
		setUrl(null);
	}
}
