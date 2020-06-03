package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.FuenteDeDatosEnfermedad;

@Local
public interface FuenteDatosBeanLocal {

//	public boolean crearFuenteDatos(String codigo, String url);
//	public boolean actualizarFuenteDatos(FuenteDeDatos fuenteDatos);
//	public List<FuenteDeDatos> obtenerFuenteDeDatos();
//	public boolean eliminarFuenteDeDatos(FuenteDeDatos fuenteDatos);

	public List<FuenteDeDatosEnfermedad> obtenerTodosFuenteDeDatosEnfermedad();
	public List<FuenteDeDatosEnfermedad> obtenerFuentesPorEnfermedad(Enfermedad enfermedad);
//	public boolean crearFuenteParaEnfermedad(FuenteDeDatosEnfermedad fuenteEnfermedad);
//	public boolean eliminarFuenteDeDatosEnfermedad(int idEliminar);
}
