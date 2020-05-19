package uy.viruscontrol.bussines.interfaces;

import javax.ejb.Remote;

@Remote
public interface PrestadorBeanRemote {

	boolean nuevoPrestador(String nombre);

}
