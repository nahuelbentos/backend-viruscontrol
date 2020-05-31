package uy.viruscontrol.bussines;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import uy.viruscontrol.bussines.interfaces.FuenteDatosBeanRemote;
import uy.viruscontrol.model.dao.interfaces.FuenteDeDatosDAOLocal;
import uy.viruscontrol.model.entities.FuenteDeDatos;
/**
 * Session Bean implementation class FuenteDatosBean
 */
@Stateless
@LocalBean
public class FuenteDatosBean implements FuenteDatosBeanRemote {

   @EJB FuenteDeDatosDAOLocal daoFuenteDatosLocal;
   
   
    public FuenteDatosBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean crearFuenteDatos(String codigo, String url) {
    	
    	if(daoFuenteDatosLocal.findAll().isEmpty()) {
    		FuenteDeDatos fuente = new FuenteDeDatos();
    		fuente.setCodigo(codigo);
    		fuente.setUrl(url);
    		
    		daoFuenteDatosLocal.persist(fuente);
    		System.out.println("Fuente de Datos con codigo "+ codigo +" creada.");
    		return true;
    	}else {
    		List<FuenteDeDatos> fuentesList = daoFuenteDatosLocal.findAll();
    		for(FuenteDeDatos fd : fuentesList) {
    			if((fd.getCodigo().contentEquals(codigo)) && (fd.isDeleted())) {
    				fd.setDeleted(false);
    				fd.setCodigo(codigo);
    				fd.setUrl(url);
    				
    				daoFuenteDatosLocal.merge(fd);
    				System.out.println("Fuente de Datos con codigo "+ codigo +" creada.");
    				return true;
    				
    			}else {
    				if((fd.getCodigo().contentEquals(codigo)) && (!fd.isDeleted())) {
    					System.out.println("La Fuente de Datos ya existe");
        				return false;
    				}
    			}
    		}
    		FuenteDeDatos fuente = new FuenteDeDatos();
    		fuente.setCodigo(codigo);
    		fuente.setUrl(url);
    		daoFuenteDatosLocal.persist(fuente);
    		System.out.println("Fuente de Datos con codigo "+ codigo +" creada.");
    		return true;
    	}
    }
    
    
    @Override
	public boolean actualizarFuenteDatos(FuenteDeDatos fuenteDatos) {
		
    	FuenteDeDatos fuente = new FuenteDeDatos();
    	fuente=daoFuenteDatosLocal.findById(fuenteDatos.getId());
		
		if(fuente != null) {
			fuente.setCodigo(fuenteDatos.getCodigo());
			fuente.setUrl(fuenteDatos.getUrl());
			daoFuenteDatosLocal.merge(fuente);
			System.out.println("Fuente de Datos actualizada correctamente.");
			return true;
		}else {
			System.out.println("Error, la Fuente de Datos no existe.");
			return false;
		}
	}
    
    @Override
    public List<FuenteDeDatos> obtenerFuenteDeDatos(){
    	
    	 List<FuenteDeDatos> fuentesDeDatos = daoFuenteDatosLocal.findAll();
    	 List<FuenteDeDatos> fuentesDeDatosExamenNoEliminadas = new ArrayList<FuenteDeDatos>();
    	for(FuenteDeDatos fuente : fuentesDeDatos)
    		if(!fuente.isDeleted())
    			fuentesDeDatosExamenNoEliminadas.add(fuente);
    	
    	return (fuentesDeDatosExamenNoEliminadas != null) ? fuentesDeDatosExamenNoEliminadas : new ArrayList<FuenteDeDatos>();
	 }

	@Override
	public boolean eliminarFuenteDeDatos(FuenteDeDatos fuenteDatos) {
		FuenteDeDatos fuente = new FuenteDeDatos();
		fuente=daoFuenteDatosLocal.findById(fuenteDatos.getId());
		
		if(fuente != null) {
			fuente.setDeleted(true);
			daoFuenteDatosLocal.merge(fuente);
			System.out.println("Fuente de Datos eliminada.");
			return true;
		}else {
			System.out.println("Error, la Fuente de Datos no existe.");
			return false;
		}
	}
}
