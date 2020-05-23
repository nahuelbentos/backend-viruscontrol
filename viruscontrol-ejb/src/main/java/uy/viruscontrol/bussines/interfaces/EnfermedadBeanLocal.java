package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Sintoma;

@Local
public interface EnfermedadBeanLocal {

	
	int altaRecursoRecomendado(String nombreEnfermedad, String nombreRecurso, boolean recursoTrata,
			boolean recursoPreviene);
	
	boolean crearEnfermedadInfecciosa(String nombreEnfermedad, String nombreTipoEnfermedad, 
    		String nombreAgente, List<Sintoma> sintomas, boolean aprobada);

	boolean aprobarEnfermedadInfecciosa(int idEnfermedad);

	int getIdSintomaByName(String nombreSintoma);

	int getIdTipoByName(String nombreTipoEnfermedad);

	int getIdRecursoByName(String nombreRecurso);

	int getIdEnfermedadByName(String nombreEnfermedad);
	
	public List<Sintoma> obtenerListaSintomas();
	
}
