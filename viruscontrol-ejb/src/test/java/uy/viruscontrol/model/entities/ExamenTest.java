package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ExamenTest {

	
	@Mock
	Examen expected;
	@Mock 
	Enfermedad enfermedad;
	
	@Before

    public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	    
		enfermedad = new Enfermedad("enfermedadTest", false, null, null, null,false,1);
		expected = new Examen(enfermedad);
		expected.setNombre("nombreTest");
	}
	
	@Test
	public void testExamen() {
		Examen actual = new Examen();
		assertEquals(expected.getClass(),actual.getClass());
	}

	@Test
	public void testExamenEnfermedad() {
		Examen actual = new Examen(enfermedad);
		assertEquals(expected.getClass(),actual.getClass());
	}

	@Test
	public void testGetNombre() {
		Examen actual = new Examen(enfermedad);
		actual.setNombre("nombreTest");
		assertEquals("nombreTest", actual.getNombre());
	}

	@Test
	public void testSetNombre() {
		Examen actual = new Examen(enfermedad);
		actual.setNombre("nombreTest");
		assertEquals("nombreTest", actual.getNombre());
	}

	
	@Test
	public void testGetEnfermedad() {
		assertEquals("enfermedadTest", expected.getEnfermedad().getNombre());
	}

	@Test
	public void testSetEnfermedad() {
		enfermedad.setNombre("enfermedadTest2");
		Examen actual = new Examen();
		actual.setEnfermedad(enfermedad);
		assertEquals("enfermedadTest2", actual.getEnfermedad().getNombre());
		
	}

}
