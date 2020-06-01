package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Remote;

import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.FuenteDeDatos;
import uy.viruscontrol.model.entities.FuenteDeDatosEnfermedad;

@Remote
public interface FuenteDatosBeanRemote {

	boolean crearFuenteDatos(String codigo, String url);

	boolean actualizarFuenteDatos(FuenteDeDatos fuenteDatos);

	List<FuenteDeDatos> obtenerFuenteDeDatos();

	boolean eliminarFuenteDeDatos(FuenteDeDatos fuenteDatos);

	public List<FuenteDeDatosEnfermedad> obtenerTodosFuenteDeDatosEnfermedad();
	
	public List<FuenteDeDatosEnfermedad> obtenerFuentesPorEnfermedad(Enfermedad enfermedad);

	public boolean crearFuenteParaEnfermedad(FuenteDeDatosEnfermedad fuenteEnfermedad);

	public boolean eliminarFuenteDeDatosEnfermedad(int idEliminar);
}
