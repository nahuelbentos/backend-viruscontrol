package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Remote;

import uy.viruscontrol.model.entities.Sintoma;

@Remote
public interface EnfermedadBeanRemote {

	public boolean crearEnfermedadInfecciosa(String nombreEnfermedad, String nombreTipoEnfermedad, 
    		String nombreAgente, List<Sintoma> sintomas, boolean aprobada);

	public boolean aprobarEnfermedadInfecciosa(int idEnfermedad);

	public List<Sintoma> obtenerListaSintomas();

}
