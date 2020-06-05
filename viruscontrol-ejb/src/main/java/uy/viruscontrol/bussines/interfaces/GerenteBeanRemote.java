package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Remote;

import uy.viruscontrol.model.entities.Caso;

@Remote
public interface GerenteBeanRemote {
	
	List<Caso> obtenerCasos();

}
