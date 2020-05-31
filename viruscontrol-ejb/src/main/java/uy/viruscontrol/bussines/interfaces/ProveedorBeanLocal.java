package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.model.entities.ProveedorRecursos;

@Local
public interface ProveedorBeanLocal {

	boolean nuevoProveedor(int tipo, String Nombre, String direccion, String barrio, String rangoHorario);

	List<ProveedorRecursos> obtenerProveedoresRecursos();
	List<ProveedorExamen> obtenerProveedoresExamenes();
	boolean actualizarProveedorExamen(ProveedorExamen proveedorExamen);
	boolean actualizarProveedorRecursos(ProveedorRecursos proveedorRecurso);
}
