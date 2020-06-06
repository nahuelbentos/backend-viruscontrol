package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Caso;

@Local
public interface GerenteBeanLocal {

	List<Caso> obtenerCasos();

}
