package uy.viruscontrol.bussines.interfaces;

import javax.ejb.Remote;

@Remote
public interface ProveedorBeanRemote {
	boolean nuevoProveedor(int tipo, String Nombre, String direccion, String barrio, String rangoHorario);
}
