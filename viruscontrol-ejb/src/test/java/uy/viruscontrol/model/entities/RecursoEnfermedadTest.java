package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RecursoEnfermedadTest {

	@Mock
	RecursoEnfermedad expected, actual;
	@Mock
	Enfermedad enfermedad;
	@Mock
	Recurso recurso;
	@Mock
	IdRecursoEnfermedad idRecursoEnfermedad;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
		
		
		expected = new RecursoEnfermedad();
		expected.setEnfermedad(enfermedad);
		expected.setRecurso(recurso);
		
		idRecursoEnfermedad = new IdRecursoEnfermedad (enfermedad.getId(), recurso.getId());
		expected.setId(idRecursoEnfermedad);
		expected.setRecursoPreviene(true);
		expected.setRecursoTrata(false);
		
		actual = new RecursoEnfermedad();
	}
	
	@Test
	public void testRecursoEnfermedadRecursoEnfermedad() {
		actual = new RecursoEnfermedad();
		actual.setEnfermedad(enfermedad);
		actual.setRecurso(recurso);
		
		idRecursoEnfermedad = new IdRecursoEnfermedad (enfermedad.getId(), recurso.getId());
		actual.setId(idRecursoEnfermedad);
		actual.setRecursoPreviene(true);
		actual.setRecursoTrata(false);
		
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getEnfermedad(), actual.getEnfermedad());
		assertEquals(expected.getRecurso(), actual.getRecurso());
		assertEquals(expected.isRecursoPreviene(), actual.isRecursoPreviene());
		assertEquals(expected.isRecursoTrata(), actual.isRecursoTrata());
		
	}

}
