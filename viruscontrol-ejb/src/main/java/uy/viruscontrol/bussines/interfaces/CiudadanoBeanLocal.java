package uy.viruscontrol.bussines.interfaces;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Sintoma;

@Local
public interface CiudadanoBeanLocal {
	public boolean solicitarMedicoADomicilio(int idCiudadano, int idMedico, Calendar fecha, List<Sintoma> sintomas);
}
