package uy.viruscontrol.bussines;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uy.viruscontrol.bussines.interfaces.GerenteBeanLocal;
import uy.viruscontrol.model.entities.Caso;

public class GerenteBeanTest {

	@Mock
	GerenteBeanLocal mockedBeanLocalGerente;
	@Mock
	List<String> mockedReceptores;
	@Mock
	List<Caso> mockedCasosList;
	
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void testMandarMailStringStringString() {
		mockedBeanLocalGerente.mandarMail("receptorTest", "asuntoTest", "mensajeTest");
		Mockito.verify(mockedBeanLocalGerente).mandarMail("receptorTest", "asuntoTest", "mensajeTest");
		
		
	}

	@Test
	public void testMandarMailListOfStringStringString() {
		mockedBeanLocalGerente.mandarMail(mockedReceptores, "asuntoTest", "mensajeTest");
		Mockito.verify(mockedBeanLocalGerente).mandarMail(mockedReceptores, "asuntoTest", "mensajeTest");
	}

	@Test
	public void testObtenerCasos() {
		mockedCasosList = mockedBeanLocalGerente.obtenerCasos();
		Mockito.verify(mockedBeanLocalGerente).obtenerCasos();
	}

}
