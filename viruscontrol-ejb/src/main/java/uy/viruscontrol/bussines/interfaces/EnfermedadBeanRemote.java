package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Remote;

import uy.viruscontrol.model.entities.Sintoma;

@Remote
public interface EnfermedadBeanRemote {

	boolean crearEnfermedadInfecciosa(String nombreEnfermedad, String nombreTipoEnfermedad, 
    		String nombreAgente, List<Sintoma> sintomas, boolean aprobada);

	boolean aprobarEnfermedadInfecciosa(int idEnfermedad);

	List<Sintoma> obtenerListaSintomas();
	
	int altaRecursoRecomendado(String nombreEnfermedad, String nombreRecurso, boolean recursoTrata, boolean recursoPreviene);

}
