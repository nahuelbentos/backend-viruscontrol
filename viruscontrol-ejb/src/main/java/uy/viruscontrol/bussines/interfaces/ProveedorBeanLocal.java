package uy.viruscontrol.bussines.interfaces;

import javax.ejb.Local;

@Local
public interface ProveedorBeanLocal {

	boolean nuevoProveedor(int tipo, String Nombre, String direccion, String barrio, String rangoHorario);

}
