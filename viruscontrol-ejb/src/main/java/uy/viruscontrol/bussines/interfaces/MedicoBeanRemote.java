package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Remote;

import uy.viruscontrol.model.entities.Examen;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.utils.DtEnfermedad;
import uy.viruscontrol.utils.DtExamen;
import uy.viruscontrol.utils.DtProveedorExamen;

@Remote
public interface MedicoBeanRemote {
	
	List<DtExamen> obtenerExamenesDeEnfermedad(int idEnfermedad);
	List<DtProveedorExamen> ObtenerProveedoresExamen();
	boolean nuevoCaso(int idDepartamento, int idExamen, int idEnfermedad,int idPaciente,int idMedico);
	
	List<DtEnfermedad> enfermerdadesAprobadas();
	List<DtExamen> examenesEnfermedad(int idEnfermedad);
}
