package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Remote;

import uy.viruscontrol.model.entities.Medico;

@Remote
public interface PrestadorBeanRemote {

	boolean nuevoPrestador(String nombre);
	
	public List<Medico> obtenerMedicosVisita(int idCiudadano);
	
}
