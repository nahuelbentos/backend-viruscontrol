package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.utils.DtExamen;

@Local
public interface MedicoBeanLocal {

	List<DtExamen> obtenerExamenesDeEnfermedad(int idEnfermedad);

	List<Ciudadano> mostrarCiudadanos();

	List<ProveedorExamen> ObtenerProveedoresExamen(int idEnfermedad);

	boolean nuevoCaso(int idDepartamento, int idExamen, int idEnfermedad,int idPaciente,int idMedico);

}
