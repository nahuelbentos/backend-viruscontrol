package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Departamento;
import uy.viruscontrol.model.entities.Examen;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.utils.DtEnfermedad;
import uy.viruscontrol.utils.DtExamen;
import uy.viruscontrol.utils.DtProveedorExamen;
import uy.viruscontrol.utils.VisitaPendiente;

@Local
public interface MedicoBeanLocal {

	List<DtExamen> obtenerExamenesDeEnfermedad(int idEnfermedad);

	List<Ciudadano> mostrarCiudadanos();

	List<DtProveedorExamen> ObtenerProveedoresExamen();

	boolean nuevoCaso(int idDepartamento, int idExamen, int idEnfermedad,int idPaciente,int idMedico,int idProveedorExamen);
	
	public List<VisitaPendiente> getVisitaPendiente(String username);

	public boolean confirmarVisitaPendiente(String username, int idVisitaPendiente);

	List<DtEnfermedad> enfermerdadesAprobadas();

	List<DtExamen> examenesEnfermedad(int idEnfermedad);

	List<Departamento> obtenerDepartamentos();
	
	
	
}
