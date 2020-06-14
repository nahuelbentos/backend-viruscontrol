package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PrestadoraSaludTest {

	@Mock
	PrestadoraSalud actual, expected;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
		expected = new PrestadoraSalud();
		expected.setId(100);
		expected.setNombre("nombre");
		expected.setDeleted(false);
		expected.setIdPrestadoraRucaf(100);
		
		actual = new PrestadoraSalud();
	}
	
	@Test
	public void testPrestadoraSaludIntString() {
		actual.setId(100);
		actual.setNombre("nombre");
		actual.setDeleted(false);
		actual.setIdPrestadoraRucaf(100);
		
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getNombre(), actual.getNombre());
		assertEquals(expected.isDeleted(), actual.isDeleted());
		assertEquals(expected.getIdPrestadoraRucaf(), actual.getIdPrestadoraRucaf());
	}

}
