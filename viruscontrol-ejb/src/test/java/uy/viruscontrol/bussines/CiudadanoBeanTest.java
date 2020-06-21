package uy.viruscontrol.bussines;



import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uy.viruscontrol.bussines.interfaces.CiudadanoBeanLocal;
import uy.viruscontrol.model.entities.Sintoma;
import uy.viruscontrol.model.entities.Ubicacion;
import uy.viruscontrol.utils.DtExamenCiudadano;
import uy.viruscontrol.utils.DtVisita;
import uy.viruscontrol.utils.firebase.NotificationPriority;

public class CiudadanoBeanTest {
	
	
	@Mock
	CiudadanoBeanLocal mockedBeanLocalCiudadano;
	@Mock 
	Sintoma mockedSintoma1, mockedSintoma2;
	@Mock
	DtVisita mockedDtVisita;
	@Mock
	List<DtExamenCiudadano> mockedDtExamenCiudadanoList;
	@Mock
	List<String> mockedStringList;
	
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
	}


	@Test
	public void testSolicitarMedicoADomicilio() {
		
		List<Sintoma> sintomas = new ArrayList<Sintoma>();
		mockedSintoma1.setNombre("sintoma 1");
		mockedSintoma2.setNombre("Sintoma 2");
		sintomas.add(mockedSintoma1);
		sintomas.add(mockedSintoma2);
		
		mockedDtVisita = mockedBeanLocalCiudadano.solicitarMedicoADomicilio(105, sintomas);
		
		Mockito.verify(mockedBeanLocalCiudadano).solicitarMedicoADomicilio(105, sintomas);
		
		
	}

	@Test
	public void testObtenerExamenesCiudadano() {
		
		mockedDtExamenCiudadanoList = mockedBeanLocalCiudadano.obtenerExamenesCiudadano(105);
		
		Mockito.verify(mockedBeanLocalCiudadano).obtenerExamenesCiudadano(105);
		
	}

	@Test
	public void testSuscribirseARecurso() {
		//Stubbing de void methods
		//Mockito.doThrow(new RuntimeException()).when(mockedBeanLocalCiudadano).suscribirseARecurso(105, "Centro", "Recurso 1");
		Mockito.doNothing().when(mockedBeanLocalCiudadano).suscribirseARecurso(Mockito.isA(Integer.class), Mockito.isA(String.class),Mockito.isA(String.class));
		mockedBeanLocalCiudadano.suscribirseARecurso(0, "", "");
		Mockito.verify(mockedBeanLocalCiudadano, Mockito.times(1)).suscribirseARecurso(0,"","");
	}

	@Test
	public void testObtenerBarrios() {
		mockedStringList = mockedBeanLocalCiudadano.obtenerBarrios();
		Mockito.verify(mockedBeanLocalCiudadano).obtenerBarrios();
	}

	@Test
	public void testObtenerCiudades() {
		mockedStringList = mockedBeanLocalCiudadano.obtenerCiudades();
		Mockito.verify(mockedBeanLocalCiudadano).obtenerCiudades();
	}

	@Test
	public void testReportarUbicacion() {
		
		Ubicacion ubicacion = Mockito.mock(Ubicacion.class);
		Mockito.doNothing().when(mockedBeanLocalCiudadano).reportarUbicacion(Mockito.isA(Ubicacion.class), Mockito.isA(Integer.class));
		mockedBeanLocalCiudadano.reportarUbicacion(ubicacion, 0);
		
		Mockito.verify(mockedBeanLocalCiudadano, Mockito.times(1)).reportarUbicacion(ubicacion, 0);
	}

	@Test
	public void testActualizarTokenPushNotifications() {
		Mockito.doNothing().when(mockedBeanLocalCiudadano).actualizarTokenPushNotifications(Mockito.isA(Integer.class), Mockito.isA(String.class));
		mockedBeanLocalCiudadano.actualizarTokenPushNotifications(0, "");
		Mockito.verify(mockedBeanLocalCiudadano, Mockito.times(1)).actualizarTokenPushNotifications(0, "");
	}

	@Test
	public void testSendPushNotification() {
		NotificationPriority notPrioridad = null;//No se puede mockear por ser una clase primitiva
		Mockito.doNothing().when(mockedBeanLocalCiudadano).sendPushNotification(Mockito.isA(Integer.class), Mockito.isA(String.class), Mockito.isA(String.class),Mockito.isA(NotificationPriority.class));
		mockedBeanLocalCiudadano.sendPushNotification(0, "", "", notPrioridad);
		
		Mockito.verify(mockedBeanLocalCiudadano, Mockito.times(1)).sendPushNotification(0,"","", notPrioridad);
	}

}
