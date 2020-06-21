package uy.viruscontrol.bussines.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.bussines.enumerated.TipoNotificacion;
import uy.viruscontrol.model.entities.Caso;
import uy.viruscontrol.model.entities.ConfiguracionNotificaciones;

@Local
public interface GerenteBeanLocal {

	List<Caso> obtenerCasos();

	void mandarMail(String receptor, String asunto, String mensaje);
	
	void mandarMail(List<String> receptores, String asunto, String mensaje);

	void configurarNotificacion(boolean notificarCiudadano,boolean notificarMedico,boolean notificarGerentes,TipoNotificacion tipo);

	List<ConfiguracionNotificaciones> obtenerConfuracionNotificacion();
	
}
