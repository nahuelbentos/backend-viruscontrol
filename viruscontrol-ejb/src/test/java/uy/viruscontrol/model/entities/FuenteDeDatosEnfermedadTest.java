package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FuenteDeDatosEnfermedadTest {

	@Mock
	FuenteDeDatosEnfermedad expected, actual;
	@Mock
	Enfermedad enfermedad;
	@Mock
	FuenteDeDatos fuente;
	
	@Before

    public void setUp() throws Exception {

       MockitoAnnotations.initMocks(this);
       
       expected = new FuenteDeDatosEnfermedad();
       expected.setEnfermedad(enfermedad);
       expected.setFiltros("filtros");
       expected.setFuente(fuente);
       expected.setId(100);
       expected.setUrl("www.url.com");
       
       actual = new FuenteDeDatosEnfermedad();
         
    }
	
	@Test
	public void testFuenteDeDatosEnfermedad() {
		assertEquals(expected.getClass(), actual.getClass());
	}

	@Test
	public void testGetId() {
		actual.setEnfermedad(enfermedad);
		actual.setFiltros("filtros");
		actual.setFuente(fuente);
		actual.setId(100);
		actual.setUrl("www.url.com");
		
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getEnfermedad(), actual.getEnfermedad());
		assertEquals(expected.getFiltros(), actual.getFiltros());
		assertEquals(expected.getFuente(), actual.getFuente());
		assertEquals(expected.getUrl(), actual.getUrl());
	}

	

}
