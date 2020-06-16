package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProveedorRecursosTest {

	@Mock
	ProveedorRecursos expected, actual;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
		expected = new ProveedorRecursos();
		expected.setId(500);
		expected.setNombre("nombre");
		expected.setRangoHorario("12 hs");
		expected.setBarrio("barrio");
		expected.setCiudad("ciudad");
		expected.setDireccion("direccion 1234");
		expected.setDeleted(false);
		
		actual = new ProveedorRecursos();
	}
	
	@Test
	public void testProveedorRecursosIntStringStringStringString() {
		actual.setId(500);
		actual.setNombre("nombre");
		actual.setRangoHorario("12 hs");
		actual.setBarrio("barrio");
		actual.setCiudad("ciudad");
		actual.setDireccion("direccion 1234");
		actual.setDeleted(false);
		
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getNombre(), actual.getNombre());
		assertEquals(expected.getBarrio(), actual.getBarrio());
		assertEquals(expected.getCiudad(), actual.getCiudad());
		assertEquals(expected.getDireccion(), actual.getDireccion());
		assertEquals(expected.getRangoHorario(), actual.getRangoHorario());
		assertEquals(expected.isDeleted(), actual.isDeleted());
	}

}
