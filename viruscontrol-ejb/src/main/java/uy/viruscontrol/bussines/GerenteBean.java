package uy.viruscontrol.bussines;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import uy.viruscontrol.bussines.interfaces.GerenteBeanLocal;
import uy.viruscontrol.bussines.interfaces.GerenteBeanRemote;
import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.entities.Caso;

/**
 * Session Bean implementation class GerenteBean
 */
@Stateful
@LocalBean
public class GerenteBean implements GerenteBeanRemote, GerenteBeanLocal {

	@EJB private CasoDAOLocal casoDao;
	
    /**
     * Default constructor. 
     */
    public GerenteBean() {
        // TODO Auto-generated constructor stub
    }
    
   
    @Override
    public List<Caso> obtenerCasos(){
    	return casoDao.findAll();
    }
    

}
