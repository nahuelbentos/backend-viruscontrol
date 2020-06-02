package uy.viruscontrol.bussines;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import uy.viruscontrol.bussines.interfaces.FuenteDatosBeanLocal;
import uy.viruscontrol.bussines.interfaces.FuenteDatosBeanRemote;
import uy.viruscontrol.model.dao.interfaces.FuenteDeDatosDAOLocal;
import uy.viruscontrol.model.dao.interfaces.FuenteDeDatosEnfermedadDAOLocal;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.FuenteDeDatos;
import uy.viruscontrol.model.entities.FuenteDeDatosEnfermedad;
/**
 * Session Bean implementation class FuenteDatosBean
 */
@Stateless
@LocalBean
@Local(FuenteDatosBeanLocal.class)
@Remote(FuenteDatosBeanRemote.class)
public class FuenteDatosBean implements FuenteDatosBeanRemote, FuenteDatosBeanLocal {

   @EJB FuenteDeDatosDAOLocal daoFuenteDatosLocal;
   @EJB private FuenteDeDatosEnfermedadDAOLocal daoFuenteEnfermedad;
   
   
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
	
	@Override
	public List<FuenteDeDatosEnfermedad> obtenerTodosFuenteDeDatosEnfermedad() {
		List<FuenteDeDatosEnfermedad> ret = daoFuenteEnfermedad.findAll();
		if (ret != null)
			return ret;
		else
			return new ArrayList<FuenteDeDatosEnfermedad>();
	}
	
	@Override
	public List<FuenteDeDatosEnfermedad> obtenerFuentesPorEnfermedad(Enfermedad enfermedad) {
		List<FuenteDeDatosEnfermedad> ret = daoFuenteEnfermedad.findAllByEnfermedad(enfermedad);
		if (ret != null)
			return ret;
		else
			return new ArrayList<FuenteDeDatosEnfermedad>();
	}

	@Override
	public boolean crearFuenteParaEnfermedad(FuenteDeDatosEnfermedad fuenteEnfermedad) {
		try {
			daoFuenteEnfermedad.persist(fuenteEnfermedad);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("["+getClass().getCanonicalName()+"] Ocurrió un error al persistir. Habilitar la traza para mayor detalle.");
			return false;
		}
	}

	@Override
	public boolean eliminarFuenteDeDatosEnfermedad(int idEliminar) {
		try {
			daoFuenteEnfermedad.delete(daoFuenteEnfermedad.findById(idEliminar));
			return true;
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("["+getClass().getCanonicalName()+"] ERROR: No se pudo eliminar la fuente de datos de la enfermedad. Habilitar la traza para mas información");
			return false;
		}
	}
}
