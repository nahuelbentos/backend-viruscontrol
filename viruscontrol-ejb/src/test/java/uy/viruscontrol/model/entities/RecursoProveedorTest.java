package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RecursoProveedorTest {

	@Mock
	RecursoProveedor expected, actual;
	@Mock
	ProveedorRecursos proveedor;
	@Mock
	Recurso recurso;
	@Mock
	IdRecursoProveedor idRecursoProveedor;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
		expected = new RecursoProveedor();
		expected.setProveedor(proveedor);
		expected.setCantidad(100);
		expected.setRecurso(recurso);
		
		actual = new RecursoProveedor();
	}
	
	@Test
	public void testRecursoProveedorRecursoProveedorRecursosInt() {
		actual = new RecursoProveedor();
		actual.setProveedor(proveedor);
		actual.setCantidad(100);
		actual.setRecurso(recurso);
		
		assertEquals(expected.getCantidad(), actual.getCantidad());
		assertEquals(expected.getProveedor(), actual.getProveedor());
		assertEquals(expected.getRecurso(), actual.getRecurso());
	}

}
