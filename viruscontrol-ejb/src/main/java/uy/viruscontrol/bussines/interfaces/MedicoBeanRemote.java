package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Remote;

import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.utils.DtExamen;

@Remote
public interface MedicoBeanRemote {
	
	List<DtExamen> obtenerExamenesDeEnfermedad(int idEnfermedad);
	List<ProveedorExamen> ObtenerProveedoresExamen(int idEnfermedad);
	boolean nuevoCaso(int idDepartamento, int idExamen, int idEnfermedad);

}
