package uy.viruscontrol.bussines.map;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import uy.viruscontrol.bussines.enumerated.TipoCaso;
import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.entities.Caso;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@LocalBean
public class MapaInteractivoBean implements MapaInteractivoBeanLocal {

	@EJB 
    private CasoDAOLocal casosDao;
    
    private List<DepartamentoEnMapa> mapa;
    private MapaInteractivo mapaInteractivo;
    
    public MapaInteractivoBean() {}

	@SuppressWarnings("null")
	@Override
	@Lock(LockType.WRITE)
	public void loadCasosOnMapa() {
		List<Caso> casos = casosDao.findAllOrderByDepartamento();
		
		// inicializo auxiliares.
		String dptoStr = null, enfStr = null;
		TipoCaso tipoCasos = null;
		int cantidad = 0;
		
		DepartamentoEnMapa dpto = null;
		EnfermedadEnMapa enf = null;
		CasoEnMapa casosEnMapa = null;
		this.mapa = new ArrayList<DepartamentoEnMapa>();
		for (Caso caso : casos) {
			
			System.out.println("Agrego caso " + caso.getId() + " al mapa.");
			System.out.println("00 " + caso.getDepartamento().getNombre());
			if (dptoStr == null || !caso.getDepartamento().getNombre().equals(dptoStr)) {
				if (dptoStr != null) {				/**  Si no es nulo es porque terminó de cargar los casos  **/
					casosEnMapa.setCantidad(cantidad);
					enf.addCaso(casosEnMapa);
					dpto.addEnfermedad(enf);
					this.mapa.add(dpto);			/**  del primer departamento y de los siguientes menos    **/
					dptoStr = null;
					enfStr = null;
					tipoCasos = null;
					cantidad = 0;
					
				}									/**  el último.											  **/
				
				dpto = new DepartamentoEnMapa();
				if (dpto != null)
					dpto.setNombre(caso.getDepartamento().getNombre());
			}
			
			
			if (enfStr == null || !caso.getEnfermedad().getNombre().equals(enfStr)) {
				//if (enfStr != null)
				//	dpto.addEnfermedad(enf);
			
				enf = new EnfermedadEnMapa();
				enf.setNombreEnfermedad(caso.getEnfermedad().getNombre());
				
			}
			
			
			if (tipoCasos == null || !caso.getTipoCaso().equals(tipoCasos)) {
				//if (tipoCasos != null) {
				//	casosEnMapa.setCantidad(cantidad);
				//	enf.addCaso(casosEnMapa);
				//	
				//	System.out.println("Agregue el los casos: "+casosEnMapa.getCantidad()+ ' ' +casosEnMapa.getCasoTipo());
				//	
				//}
					
					
				casosEnMapa = new CasoEnMapa();
				
				casosEnMapa.setCasoTipo(caso.getTipoCaso());
				cantidad = 0;
			}
			
			/************************* igualo auxiliares *************************/
			tipoCasos = caso.getTipoCaso();
			enfStr = caso.getEnfermedad().getNombre();
			dptoStr = caso.getDepartamento().getNombre();
			cantidad ++;
		}
		
		/**** Agrego la ultima iteración. ****/
		if (dpto != null && enf != null && casosEnMapa != null) {
			casosEnMapa.setCantidad(cantidad);
			enf.addCaso(casosEnMapa);
			dpto.addEnfermedad(enf);
			this.mapa.add(dpto);
		}
		
		if (!this.mapa.isEmpty()) {
			this.mapaInteractivo = new MapaInteractivo();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			this.mapaInteractivo.setUltimaActualizacion(sdf.format(Calendar.getInstance().getTime()));
			this.mapaInteractivo.setMapa(this.mapa);
		} else
			this.mapaInteractivo = null;
		

	}

	@Override
	public MapaInteractivo getMapa() {
		if (mapaInteractivo == null)
			this.loadCasosOnMapa();
		return this.mapaInteractivo;
	}
    
    
    

}
