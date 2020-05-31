package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Remote;

import uy.viruscontrol.model.entities.FuenteDeDatos;

@Remote
public interface FuenteDatosBeanRemote {

	boolean crearFuenteDatos(String codigo, String url);

	boolean actualizarFuenteDatos(FuenteDeDatos fuenteDatos);

	List<FuenteDeDatos> obtenerFuenteDeDatos();

	boolean eliminarFuenteDeDatos(FuenteDeDatos fuenteDatos);
}
