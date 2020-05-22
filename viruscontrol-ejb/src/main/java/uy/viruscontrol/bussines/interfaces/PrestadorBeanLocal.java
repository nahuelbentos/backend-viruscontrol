package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Medico;

@Local
public interface PrestadorBeanLocal {

	boolean nuevoPrestador(String nombre);
	
	public List<Medico> obtenerMedicosVisita(int idCiudadano);

}
