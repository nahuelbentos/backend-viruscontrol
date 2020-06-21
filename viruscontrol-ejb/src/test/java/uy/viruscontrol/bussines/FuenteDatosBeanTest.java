package uy.viruscontrol.bussines;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uy.viruscontrol.bussines.interfaces.FuenteDatosBeanLocal;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.FuenteDeDatos;
import uy.viruscontrol.model.entities.FuenteDeDatosEnfermedad;

public class FuenteDatosBeanTest {

	@Mock
	FuenteDatosBeanLocal mockedBeanLocalFuente;
	@Mock
	FuenteDeDatos mockedFuente;
	@Mock
	List<FuenteDeDatos> mockedFuentesList;
	@Mock
	List<FuenteDeDatosEnfermedad> mockedFuentesEnfermedadesList;
	@Mock
	Enfermedad mockedEnfermedad;
	@Mock
	FuenteDeDatosEnfermedad mockedFuenteEnfermedad;
	
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void testCrearFuenteDatos() {
		mockedBeanLocalFuente.crearFuenteDatos("codigoTest", "urlTest.com");
		Mockito.verify(mockedBeanLocalFuente).crearFuenteDatos("codigoTest", "urlTest.com");
	}

	@Test
	public void testActualizarFuenteDatos() {
		mockedBeanLocalFuente.actualizarFuenteDatos(mockedFuente);
		Mockito.verify(mockedBeanLocalFuente).actualizarFuenteDatos(mockedFuente);
	}
	
	@Test
	public void testObtenerFuenteDeDatos() {
		mockedFuentesList = mockedBeanLocalFuente.obtenerFuenteDeDatos();
		Mockito.verify(mockedBeanLocalFuente).obtenerFuenteDeDatos();
	}

	@Test
	public void testEliminarFuenteDeDatos() {
		mockedBeanLocalFuente.eliminarFuenteDeDatos(mockedFuente);
		Mockito.verify(mockedBeanLocalFuente).eliminarFuenteDeDatos(mockedFuente);
	}

	@Test
	public void testObtenerTodosFuenteDeDatosEnfermedad() {
		mockedFuentesEnfermedadesList = mockedBeanLocalFuente.obtenerTodosFuenteDeDatosEnfermedad();
		Mockito.verify(mockedBeanLocalFuente).obtenerTodosFuenteDeDatosEnfermedad();
	}

	@Test
	public void testObtenerFuentesPorEnfermedad() {
		mockedFuentesEnfermedadesList = mockedBeanLocalFuente.obtenerFuentesPorEnfermedad(mockedEnfermedad);
		Mockito.verify(mockedBeanLocalFuente).obtenerFuentesPorEnfermedad(mockedEnfermedad);
	}

	@Test
	public void testCrearFuenteParaEnfermedad() {
		mockedBeanLocalFuente.crearFuenteParaEnfermedad(mockedFuenteEnfermedad);
		Mockito.verify(mockedBeanLocalFuente).crearFuenteParaEnfermedad(mockedFuenteEnfermedad);
	}

	@Test
	public void testEliminarFuenteDeDatosEnfermedad() {
		mockedBeanLocalFuente.eliminarFuenteDeDatosEnfermedad(100);
		Mockito.verify(mockedBeanLocalFuente).eliminarFuenteDeDatosEnfermedad(100);
		
	}

}
