package uy.viruscontrol.bussines.interfaces;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Sintoma;
import uy.viruscontrol.model.entities.Ubicacion;
import uy.viruscontrol.utils.DtExamenCiudadano;
import uy.viruscontrol.utils.DtVisita;
import uy.viruscontrol.utils.firebase.NotificationPriority;

@Local
public interface CiudadanoBeanLocal {
	public DtVisita solicitarMedicoADomicilio(int idCiudadano, List<Sintoma> sintomas);
	List<DtExamenCiudadano> obtenerExamenesCiudadano(int idCiudadano);
	void suscribirseARecurso(int idCiudadano, String barrio,int idRecurso);
	List<String> obtenerBarrios();
	List<String> obtenerCiudades();
	public void reportarUbicacion(Ubicacion ubicacion, int idCiudadano);
	public void actualizarTokenPushNotifications(int idCiudadano, String token);
	public void sendPushNotification(int idCiudadano, String titulo, String texto, NotificationPriority prioridad);
	public HashMap<Integer, Ciudadano> obtenerCiudadanosEnfermos();
	
}
