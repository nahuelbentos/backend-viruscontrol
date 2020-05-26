package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Remote;

import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.Sintoma;
import uy.viruscontrol.model.entities.TipoRecurso;

@Remote
public interface EnfermedadBeanRemote {

	boolean crearEnfermedadInfecciosa(String nombreEnfermedad, String nombreTipoEnfermedad, 
    		String nombreAgente, List<Sintoma> sintomas, boolean aprobada);

	boolean aprobarEnfermedadInfecciosa(int idEnfermedad);

	List<Sintoma> obtenerListaSintomas();
	
	int altaRecursoRecomendado(String nombreEnfermedad, String nombreRecurso, boolean recursoTrata, boolean recursoPreviene);

	List<Enfermedad> obtenerListaEnfermedadesNoAprobadas();

	int getIdTipoRecursoByName(String nombreTipoRecurso);
	
	int getIdEnfermedadByName(String nombreEnfermedad);
	
	boolean altaRecursoDeUnDeterminadoTipo(String nombre, TipoRecurso tipoRecurso);
	
	List<Recurso> obtenerRecursosPorTipoRecurso(String nombreTipoRecurso);
}
