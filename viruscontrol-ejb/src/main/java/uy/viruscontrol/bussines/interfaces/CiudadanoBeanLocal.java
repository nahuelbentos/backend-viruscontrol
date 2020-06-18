package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Sintoma;
import uy.viruscontrol.utils.DtExamenCiudadano;
import uy.viruscontrol.utils.DtVisita;

@Local
public interface CiudadanoBeanLocal {
	public DtVisita solicitarMedicoADomicilio(int idCiudadano, List<Sintoma> sintomas);

	List<DtExamenCiudadano> obtenerExamenesCiudadano(int idCiudadano);

	void suscribirseARecurso(int idCiudadano, String barrio,String recurso);

	List<String> obtenerBarrios();

	List<String> obtenerCiudades();

	
}
