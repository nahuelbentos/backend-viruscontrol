package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class IdRecursoProveedorTest {

	
	@Mock 
	RecursoProveedor actual, expected;
	@Mock
	Recurso recurso;
	@Mock
	Proveedor proveedor;
	
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		proveedor.setId(100);
		recurso.setId(200);
		expected = new RecursoProveedor();
		
	}
	
	
	@Test
	public void testRecursoProveedor() {
		
		actual = new RecursoProveedor();
		
		assertEquals(expected.getProveedor(), actual.getProveedor());
		assertEquals(expected.getRecurso(), actual.getRecurso());
	}

}
