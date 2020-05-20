package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CiudadanoTest {

	@Mock
	Ciudadano expectedCiudadano;
	@Mock
	Ciudadano actualCiudadano;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		cal.setTime(dateFormat.parse("1985-10-05 00:00"));
		
		
		expectedCiudadano = new Ciudadano(1, "Natalie", "Di Bono", "Calle 123", cal, "Uruguaya", 
				"natalie.di@fing.edu.uy", "ndb1985", "21232F297A57A5A743894A0E4A801FC3", false);
		
		actualCiudadano = new Ciudadano();
	}
	
	@Test
	public void testCiudadano() {
		Ciudadano actual = new Ciudadano();
		assertEquals(expectedCiudadano.getClass(), actual.getClass());
	}

	@Test
	public void testCiudadanoIntStringStringStringCalendarStringStringStringStringBoolean() throws ParseException {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		cal.setTime(dateFormat.parse("1985-10-05 00:00"));
		
		Ciudadano actualCiudadano = new Ciudadano(1, "Natalie", "Di Bono", "Calle 123", cal, "Uruguaya", 
				"natalie.di@fing.edu.uy", "ndb1985", "21232F297A57A5A743894A0E4A801FC3", false);
		
		assertEquals(expectedCiudadano.getNombre(), actualCiudadano.getNombre());
	}

	@Test
	public void testGetIdUsuario() {
		assertEquals(1,expectedCiudadano.getIdUsuario() );
	}

	@Test
	public void testSetIdUsuario() {
		actualCiudadano.setIdUsuario(expectedCiudadano.getIdUsuario());
		assertEquals(expectedCiudadano.getIdUsuario(),actualCiudadano.getIdUsuario());
	}

	@Test
	public void testGetNombre() {
		assertEquals("Natalie",expectedCiudadano.getNombre());
	}

	@Test
	public void testSetNombre() {
		actualCiudadano.setNombre(expectedCiudadano.getNombre());
		assertEquals(expectedCiudadano.getNombre(),actualCiudadano.getNombre());
	}

}
