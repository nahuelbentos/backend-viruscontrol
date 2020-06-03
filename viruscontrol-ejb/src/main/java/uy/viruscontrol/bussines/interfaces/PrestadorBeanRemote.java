package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Remote;


import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.PrestadoraSalud;

@Remote
public interface PrestadorBeanRemote {

	boolean nuevoPrestador(String nombre, int idRucaf);
	
	public List<Medico> obtenerMedicosVisita(int idCiudadano);

	boolean actualizarPrestador(PrestadoraSalud prestadoraSalud);
	
	List<PrestadoraSalud> obtenerPrestadorasSalud();

	boolean eliminarPrestadoraSalud(PrestadoraSalud prestadoraSalud);
	
}
