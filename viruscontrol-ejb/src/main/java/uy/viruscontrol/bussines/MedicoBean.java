package uy.viruscontrol.bussines;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import org.apache.http.client.ClientProtocolException;

import uy.viruscontrol.bussines.enumerated.TipoCaso;
import uy.viruscontrol.bussines.interfaces.EnfermedadBeanLocal;
import uy.viruscontrol.bussines.interfaces.MedicoBeanLocal;
import uy.viruscontrol.bussines.interfaces.MedicoBeanRemote;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorExamenLocal;
import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.CiudadanoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.DepartamentoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.EnfermedadDAOLocal;
import uy.viruscontrol.model.dao.interfaces.ExamenDAOLocal;
import uy.viruscontrol.model.entities.Caso;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Departamento;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Examen;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.utils.DtExamen;

/**
 * Session Bean implementation class MedicoBean
 */
@Stateful
@LocalBean
public class MedicoBean implements MedicoBeanRemote, MedicoBeanLocal {

	@EJB private ServiceAgentProveedorExamenLocal saProvEx;
	@EJB private CiudadanoDAOLocal ciudadanoDao;
    @EJB private DepartamentoDAOLocal departamentoDao;
    @EJB private ExamenDAOLocal examenDao;
    @EJB private EnfermedadDAOLocal enfermedadDao;
    @EJB private CasoDAOLocal casoDao;
    
	/**
     * Default constructor. 
     */
    public MedicoBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public List<DtExamen> obtenerExamenesDeEnfermedad(int idEnfermedad){
    	
    	try {
			List<DtExamen> listEx = saProvEx.obtenerExamenesParaUnaEnfermedad(idEnfermedad);
			return listEx;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	List<DtExamen> listEx2= new ArrayList<DtExamen>();
		return listEx2;
    	
    }
    
    @Override
    public List<Ciudadano> mostrarCiudadanos(){
    	return ciudadanoDao.findAll();
    }
    
    @Override
    public List<ProveedorExamen> ObtenerProveedoresExamen(int idEnfermedad) {
    	try {
    		//esto va a cambiar cuando jhona cambie lo de service agents
    		List<ProveedorExamen> listaProv =saProvEx.obtenerProveedores(idEnfermedad);
    	
    	//	ProveedorExamen pe=saProvEx.obtenerProveedor(idEnfermedad);
    	
	//		listaProv.add(pe) ;
			
			
			
			return listaProv;
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	List<ProveedorExamen> listaProv2 =new ArrayList<ProveedorExamen>();
    	return listaProv2;
    }
    
    @Override
    public boolean nuevoCaso(int idDepartamento,int idExamen,int idEnfermedad,int idPaciente,int idMedico) {
    	 System.out.println("nuevo caso");
    	 try {
			DtExamen solEx = saProvEx.altaDeExamen(idPaciente, idExamen, idMedico);
		
    	 
			Departamento depa=departamentoDao.findById(idDepartamento);
		    
		    Examen ex=examenDao.findById(solEx.getId());
		    
		    Enfermedad enf=enfermedadDao.findById(idEnfermedad);
		    	
		   	Caso c=new Caso();
		   	c.setDepartamento(depa);
		   	c.setEnfermedad(enf);
		   	c.setExamen(ex);
		   	c.setTipoCaso(TipoCaso.SOSPECHOSO);
		   	
		   	casoDao.persist(c);
    	 
    	 } catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
   	return true;
    	
    }
   

}
