package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Remote;


import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.model.entities.ProveedorRecursos;

@Remote
public interface ProveedorBeanRemote {
	
	boolean nuevoProveedor(int tipo,String nombre,String direccion,String barrio,String rangoHorario, String codigoPeriferico);
	
	List<ProveedorRecursos> obtenerProveedoresRecursos();
	
	List<ProveedorExamen> obtenerProveedoresExamenes();
	
	boolean actualizarProveedorExamen(ProveedorExamen proveedorExamen);
	
	boolean actualizarProveedorRecursos(ProveedorRecursos proveedorRecurso);

	boolean eliminarProveedorRecursos(ProveedorRecursos proveedorRecurso);

	boolean eliminarProveedorExamenes(ProveedorExamen proveedorExamen);
	
	
}
