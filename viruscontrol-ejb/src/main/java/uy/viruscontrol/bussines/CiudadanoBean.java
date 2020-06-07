package uy.viruscontrol.bussines;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import uy.viruscontrol.bussines.interfaces.CiudadanoBeanLocal;
import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.CiudadanoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.MedicoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.SintomaDAOLocal;
import uy.viruscontrol.model.dao.interfaces.VisitaMedicoDAOLocal;
import uy.viruscontrol.model.entities.Sintoma;
import uy.viruscontrol.model.entities.VisitaMedico;
import uy.viruscontrol.utils.DtExamenCiudadano;

@Stateless
@Local(CiudadanoBeanLocal.class)
public class CiudadanoBean implements CiudadanoBeanLocal {
	@EJB private CiudadanoDAOLocal daoCiudadano;
	@EJB private MedicoDAOLocal daoMedico;
	@EJB private SintomaDAOLocal daoSintoma;
	@EJB private VisitaMedicoDAOLocal daoVisita;
	@EJB private CasoDAOLocal daoCasoLocal;
	
	public CiudadanoBean() {
		super();
	}

	@Override
	public boolean solicitarMedicoADomicilio(int idCiudadano, int idMedico, Calendar fecha, List<Sintoma> sintomas) {
		try {
			VisitaMedico vm = new VisitaMedico();
			vm.setMedico(daoMedico.findById(idMedico));
			vm.setCiudadano(daoCiudadano.findById(idCiudadano));
			vm.setFechaAsignacion(fecha);
			vm.setVisitaRealizada(false);
			vm.setSintomas(sintomas);
			daoVisita.persist(vm);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	@Override
	public List<DtExamenCiudadano> obtenerExamenesCiudadano(int idCiudadano){
		
		return daoCasoLocal.findAllExamenesByCiudadano(idCiudadano);
	}

}
