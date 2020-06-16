package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class IdRecursoProveedorTest {

	
	@Mock 
	IdRecursoProveedor actual, expected;
	@Mock
	Recurso recurso;
	@Mock
	Proveedor proveedor;
	
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		proveedor.setId(100);
		recurso.setId(200);
		expected = new IdRecursoProveedor (recurso , proveedor);
		
	}
	
	
	@Test
	public void testIdRecursoProveedor() {
		actual = new IdRecursoProveedor(recurso, proveedor);
		
		assertEquals(expected.getProveedor(), actual.getProveedor());
		assertEquals(expected.getRecurso(), actual.getRecurso());
	}

}
