package uy.viruscontrol.bussines;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import org.apache.http.client.ClientProtocolException;

import uy.viruscontrol.bussines.enumerated.TipoCaso;
import uy.viruscontrol.bussines.interfaces.MedicoBeanLocal;
import uy.viruscontrol.bussines.interfaces.MedicoBeanRemote;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorExamenLocal;
import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.CiudadanoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.DepartamentoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.EnfermedadDAOLocal;
import uy.viruscontrol.model.dao.interfaces.ExamenDAOLocal;
import uy.viruscontrol.model.dao.interfaces.ProveedorExamenDAOLocal;
import uy.viruscontrol.model.dao.interfaces.UsuarioDAOLocal;
import uy.viruscontrol.model.dao.interfaces.VisitaMedicoDAOLocal;
import uy.viruscontrol.model.entities.Caso;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Departamento;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Examen;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.model.entities.Sintoma;
import uy.viruscontrol.model.entities.VisitaMedico;
import uy.viruscontrol.utils.DtEnfermedad;
import uy.viruscontrol.utils.DtExamen;
import uy.viruscontrol.utils.DtProveedorExamen;
import uy.viruscontrol.utils.VisitaPendiente;

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
    @EJB private UsuarioDAOLocal usuarioDao;
    @EJB private VisitaMedicoDAOLocal visitaMedicoDao;
    @EJB private ProveedorExamenDAOLocal provExamenDao;
	/**
     * Default constructor. 
     */
    public MedicoBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public List<DtExamen> obtenerExamenesDeEnfermedad(int idEnfermedad){
    
    	//esto hablarlo con jhona
    	try {
			List<DtExamen> listEx = saProvEx.obtenerExamenesParaUnaEnfermedad(idEnfermedad);
			if(listEx!=null) {
				return listEx;
			}else {
				List<DtExamen> listEx3= new ArrayList<DtExamen>();
				return listEx3;
			}
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
    public List<DtProveedorExamen> ObtenerProveedoresExamen() {
    	
    	List<ProveedorExamen> proveedores=provExamenDao.findAll();
    	List<DtProveedorExamen> prov = new ArrayList<DtProveedorExamen>();
    	for(ProveedorExamen pe:proveedores) {
    		if(pe.isDeleted()==false) {
    			DtProveedorExamen dtpe=new DtProveedorExamen(pe.getId(), pe.getNombre(), pe.getDireccion(), pe.getBarrio(), pe.getRangoHorario());
    			prov.add(dtpe);
    		}
    		
    		
    	}
    	
    	return prov;
		/*
		 * try { //esto va a cambiar cuando jhona cambie lo de service agents
		 * List<ProveedorExamen> listaProv =saProvEx.obtenerProveedores(idEnfermedad);
		 * 
		 * // ProveedorExamen pe=saProvEx.obtenerProveedor(idEnfermedad);
		 * 
		 * // listaProv.add(pe) ;
		 * 
		 * 
		 * 
		 * return listaProv;
		 * 
		 * } catch (ClientProtocolException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 * 
		 * 
		 * List<ProveedorExamen> listaProv2 =new ArrayList<ProveedorExamen>(); return
		 * listaProv2;
		 */
    }
    
    @Override
    public boolean nuevoCaso(int idDepartamento,int idExamen,int idEnfermedad,int idPaciente,int idMedico) {
    	 System.out.println("nuevo caso");
    	 try {
			DtExamen solEx = saProvEx.altaDeExamen(idPaciente, idExamen, idMedico);
		
			System.out.println("idDepartamento: "+idDepartamento);
			System.out.println("idExamen: "+idExamen);
			System.out.println("idEnfermedad"+idEnfermedad);
			System.out.println("idexamen solex: "+solEx.getId());
			
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

	@Override
	public List<VisitaPendiente> getVisitaPendiente(String username) {
		Medico m = (Medico)usuarioDao.findByUsername(username);
		
		List<VisitaMedico> vms = visitaMedicoDao.findByMedico(m);
		List<VisitaPendiente> vps = new ArrayList<VisitaPendiente>();
		for (VisitaMedico vm : vms) {
			if (!vm.isVisitaRealizada()) {
				VisitaPendiente vp = new VisitaPendiente();
				vp.setId(vm.getIdVisitaMedico());
				vp.setNombre(vm.getCiudadano().getNombre());
				vp.setApellido(vm.getCiudadano().getApellido());
				vp.setDireccion(vm.getCiudadano().getDireccion());
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				vp.setFecha(sdf.format(vm.getFechaAsignacion().getTime()) );
				
				List<Sintoma> sintomas = new ArrayList<Sintoma>();
				for (Sintoma s : vm.getSintomas()) {
					sintomas.add(s);
				}
				vp.setSintomas(sintomas);
				
				vps.add(vp);
			}
		}

		return vps;
	}

	@Override
	public boolean confirmarVisitaPendiente(String username, int idVisitaPendiente) {
		
		VisitaMedico visita = visitaMedicoDao.findById(idVisitaPendiente);
		
		if (visita == null)
			return false;
		
		if (!visita.getMedico().getUsername().equals(username))
			return false;
		
		visita.setVisitaRealizada(true);
		
		visitaMedicoDao.merge(visita);
		return true;
		
	}
	
	
	@Override
	public List<DtEnfermedad> enfermerdadesAprobadas(){
		List<Enfermedad> enfermedades=enfermedadDao.findAll();
		List<DtEnfermedad> enfAprobadas=new ArrayList<DtEnfermedad>();
		if(enfermedades!=null) {
			
			for(Enfermedad e:enfermedades) {
				
				if(e.isAprobada()) {
					DtEnfermedad dtenf=new DtEnfermedad(e.getId(), e.getNombre());
					enfAprobadas.add(dtenf);
				}
			}
		
		}
		
		return enfAprobadas;
	}
	
	@Override
	public List<DtExamen> examenesEnfermedad(int idEnfermedad){
		
		List<Examen> examenes=examenDao.findAll();
		

		List<DtExamen> examenesenf= new ArrayList<DtExamen>();
	

		for(Examen e:examenes) {

			if(e.getEnfermedad().getId()==idEnfermedad) {
				DtExamen dtexamen= new DtExamen(e.getId(), e.getNombre(), e.getEnfermedad().getId(), e.getEnfermedad().getNombre());
				examenesenf.add(dtexamen);
			}
		}
		
	

		return examenesenf;
	}

}
