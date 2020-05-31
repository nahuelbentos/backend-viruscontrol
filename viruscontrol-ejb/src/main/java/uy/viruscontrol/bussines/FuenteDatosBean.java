package uy.viruscontrol.bussines;

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
    
}
