package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FuenteDeDatosTest {

	@Mock
	FuenteDeDatos expected, actual;
	
	
	@Before

    public void setUp() throws Exception {

       MockitoAnnotations.initMocks(this);
       expected = new FuenteDeDatos();
       actual = new FuenteDeDatos();
         
    }
	
	
	@Test
	public void testFuenteDeDatos() {
		assertEquals(expected.getClass(), actual.getClass());
	}

	@Test
	public void testFuenteDeDatosStringString() {
		actual.setCodigo("Codigo 1");
		actual.setDeleted(false);
		actual.setId(1);
		actual.setUrl("www.url.com");
		
		assertEquals("Codigo 1", actual.getCodigo());
		assertEquals(false, actual.isDeleted());
		assertEquals(1, actual.getId());
		assertEquals("www.url.com", actual.getUrl());
	}

	
}
