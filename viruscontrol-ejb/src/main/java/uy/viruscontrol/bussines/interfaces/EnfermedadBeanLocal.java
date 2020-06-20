package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.Sintoma;
import uy.viruscontrol.model.entities.TipoRecurso;


@Local
public interface EnfermedadBeanLocal {

	
	boolean asociarRecursoRecomendado(String nombreEnfermedad, String nombreRecurso, boolean recursoTrata,
			boolean recursoPreviene);
	
	boolean crearEnfermedadInfecciosa(String nombreEnfermedad, String nombreTipoEnfermedad, 
    		String nombreAgente, List<Sintoma> sintomas, boolean aprobada, float distanciaContagio);

	boolean aprobarEnfermedadInfecciosa(int idEnfermedad);

	int getIdSintomaByName(String nombreSintoma);

	int getIdTipoByName(String nombreTipoEnfermedad);

	int getIdRecursoByName(String nombreRecurso);

	int getIdEnfermedadByName(String nombreEnfermedad);
	
	int getIdTipoRecursoByName(String nombreTipoRecurso);
	
	public List<Sintoma> obtenerListaSintomas();

	boolean altaRecursoDeUnDeterminadoTipo(String nombre, int idTipoRecurso, String codigoPeriferico);

	List<Recurso> obtenerRecursosPorTipoRecurso(TipoRecurso tipoRecurso);

	List<Recurso> obtenerRecursosDisponibles();

	List<Recurso> obtenerRecursoPorEnfermedad(int idEnfermedad);

	List<TipoRecurso> obtenerTiposDeRecursos();
	
	boolean altaTipoRecurso (String nombre, String descripcion, String codigoPeriferico);
	
	List<Enfermedad> obtenerEnfermedades();
	
	List<Recurso> obtenerRecursos();
	
	List<Enfermedad> obtenerListaEnfermedadesNoAprobadas();
	
	boolean rechazarEnfermedadInfecciosa(int idEnfermedad);
	
}
