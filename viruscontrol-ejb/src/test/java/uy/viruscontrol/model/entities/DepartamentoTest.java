package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



public class DepartamentoTest {

	
	@Mock
	Departamento expected, actual;
	
	
	@Before

    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);
        
       
       expected = new Departamento();
       actual = new Departamento();
         
    }
	
	
	@Test
	public void testDepartamento() {
		assertEquals(expected.getClass(), actual.getClass());
	}

	@Test
	public void testDepartamentoString() {
		actual.setNombre("Montevideo");
		assertEquals("Montevideo", actual.getNombre());
	}

	@Test
	public void testGetIdSetId() {
		actual.setId(1);
		assertEquals(1, actual.getId());
	}

	

	@Test
	public void testGetNombreSetNombre() {
		actual.setNombre("Montevideo");
		expected.setNombre("Montevideo");
		assertEquals(expected.getNombre(), actual.getNombre());
	}

	
}
