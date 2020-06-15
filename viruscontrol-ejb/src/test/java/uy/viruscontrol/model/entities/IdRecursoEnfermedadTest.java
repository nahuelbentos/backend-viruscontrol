package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class IdRecursoEnfermedadTest {

	@Mock 
	IdRecursoEnfermedad actual, expected;
	@Mock
	Recurso recurso;
	@Mock
	Enfermedad enfermedad;
	
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		enfermedad.setId(100);
		recurso.setId(200);
		expected = new IdRecursoEnfermedad (enfermedad.getId(), recurso.getId());
		
	}
	
	
	@Test
	public void testIdRecursoEnfermedad() {
		actual = new IdRecursoEnfermedad (enfermedad.getId(), recurso.getId());
		assertEquals(expected.getIdEnfermedad(), actual.getIdEnfermedad());
		assertEquals(expected.getIdRecurso(), actual.getIdRecurso());
	}

}
