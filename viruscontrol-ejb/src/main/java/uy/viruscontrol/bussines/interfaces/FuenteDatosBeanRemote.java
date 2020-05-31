package uy.viruscontrol.bussines.interfaces;

import javax.ejb.Remote;

@Remote
public interface FuenteDatosBeanRemote {

	boolean crearFuenteDatos(String codigo, String url);
}
