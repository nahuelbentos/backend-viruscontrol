package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Remote;

import uy.viruscontrol.bussines.enumerated.TipoNotificacion;
import uy.viruscontrol.model.entities.Caso;
import uy.viruscontrol.model.entities.ConfiguracionNotificaciones;

@Remote
public interface GerenteBeanRemote {
	
	List<Caso> obtenerCasos();
	void configurarNotificacion(boolean notificarCiudadano,boolean notificarMedico,boolean notificarGerentes,TipoNotificacion tipo);
	List<ConfiguracionNotificaciones> obtenerConfuracionNotificacion();
}
