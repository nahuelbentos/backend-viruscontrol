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

public class AdministradorTest {

	@Mock
	Administrador expectedAdmin;
	@Mock
	Administrador actualdAdmin;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		cal.setTime(dateFormat.parse("1985-10-05 00:00"));
		
		
		expectedAdmin = new Administrador(1, "Natalie", "Di Bono", "Calle 123", cal, "Uruguaya", 
				"natalie.di@fing.edu.uy", "ndb1985", "21232F297A57A5A743894A0E4A801FC3", false);
		
		actualdAdmin = new Administrador();
	}
	
	@Test
	public void testAdministrador() {
		Administrador actual = new Administrador();
		assertEquals(expectedAdmin.getClass(), actual.getClass());
	}

	@Test
	public void testAdministradorIntStringStringStringCalendarStringStringStringStringBoolean() throws ParseException {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		cal.setTime(dateFormat.parse("1985-10-05 00:00"));
		
		Administrador actualAdmin = new Administrador(1, "Natalie", "Di Bono", "Calle 123", cal, "Uruguaya", 
				"natalie.di@fing.edu.uy", "ndb1985", "21232F297A57A5A743894A0E4A801FC3", false);
		
		assertEquals(expectedAdmin.getNombre(), actualAdmin.getNombre());
	}

	

	@Test
	public void testGetIdUsuario() {
		assertEquals(1,expectedAdmin.getIdUsuario() );
	}

	@Test
	public void testSetIdUsuario() {
		actualdAdmin.setIdUsuario(expectedAdmin.getIdUsuario());
		assertEquals(expectedAdmin.getIdUsuario(),actualdAdmin.getIdUsuario());
		
	}

	@Test
	public void testGetNombre() {
		assertEquals("Natalie",expectedAdmin.getNombre());
	}

	@Test
	public void testSetNombre() {
		actualdAdmin.setNombre(expectedAdmin.getNombre());
		assertEquals(expectedAdmin.getNombre(),actualdAdmin.getNombre());
	}
	

	

}
