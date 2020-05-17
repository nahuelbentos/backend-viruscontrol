package uy.viruscontrol.bussines.interfaces;

import javax.ejb.Local;

@Local
public interface PrestadorBeanLocal {

	boolean nuevoPrestador(String nombre);

}
