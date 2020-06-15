package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UbicacionTest {

	@Mock
	Ubicacion actual, expected;
	@Mock
	Ciudadano ciudadano;
	
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
		expected = new Ubicacion();
		expected.setCiudadano(ciudadano);
		expected.setId(100);
		expected.setLatitud("latitud");
		expected.setLongitud("longitud");
		
		actual = new Ubicacion();
	}
	
	@Test
	public void testUbicacionIntStringStringCiudadano() {
		
		actual.setCiudadano(ciudadano);
		actual.setId(100);
		actual.setLatitud("latitud");
		actual.setLongitud("longitud");
		
		assertEquals(expected.getCiudadano(), actual.getCiudadano());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getLatitud(), actual.getLatitud());
		assertEquals(expected.getLongitud(), actual.getLongitud());
	}

}
