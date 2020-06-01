package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Local;


import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.PrestadoraSalud;

@Local
public interface PrestadorBeanLocal {

	boolean nuevoPrestador(String nombre, int idRucaf);
	
	public List<Medico> obtenerMedicosVisita(int idCiudadano);

	boolean actualizarPrestador(PrestadoraSalud prestadoraSalud);

	List<PrestadoraSalud> obtenerPrestadorasSalud();

}
