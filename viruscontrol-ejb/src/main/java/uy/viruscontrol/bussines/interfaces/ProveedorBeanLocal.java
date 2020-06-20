package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.model.entities.ProveedorRecursos;

@Local
public interface ProveedorBeanLocal {

	boolean nuevoProveedor(int tipo,String nombre,String direccion,String ciudad, String barrio,String rangoHorario, String codigoPeriferico);

	List<ProveedorRecursos> obtenerProveedoresRecursos();
	
	List<ProveedorExamen> obtenerProveedoresExamenes();
	
	boolean actualizarProveedorExamen(ProveedorExamen proveedorExamen);
	
	boolean actualizarProveedorRecursos(ProveedorRecursos proveedorRecurso);
	
	boolean altaRecursoProveedor(String codigoProveedor, String nombreRecurso);
	
	boolean eliminarProveedorRecursos(ProveedorRecursos proveedorRecurso);
	
	boolean eliminarProveedorExamenes(ProveedorExamen proveedorExamen);
}
