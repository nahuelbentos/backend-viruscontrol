package uy.viruscontrol.model.entities;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TipoRecursoTest {

	@Mock
	TipoRecurso expected, actual;
	
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		expected = new TipoRecurso();
		
		expected.setCodigoPeriferico("codigo1");
		expected.setId(100);
		expected.setNombre("nombre");
		expected.setDescripcion("descripcion");
		
		actual = new TipoRecurso();
	}
	
	@Test
	public void testTipoRecursoStringString() {
		actual.setCodigoPeriferico("codigo1");
		actual.setId(100);
		actual.setNombre("nombre");
		actual.setDescripcion("descripcion");
		
		assertEquals(expected.getCodigoPeriferico(), actual.getCodigoPeriferico());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getNombre(), actual.getNombre());
		assertEquals(expected.getDescripcion(), actual.getDescripcion());
	}

}
