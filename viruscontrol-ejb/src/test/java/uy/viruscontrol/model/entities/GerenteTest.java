package uy.viruscontrol.model.entities;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GerenteTest {

	@Mock 
	Gerente expected, actual;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
		expected = new Gerente();
		expected.setIdUsuario(100);
		expected.setUsername("username");
		expected.setNombre("Nombre");
		expected.setApellido("apellido");
		expected.setCorreo("mail@mail.com");
		
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			cal.setTime(dateFormat.parse("1985-10-05 00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		expected.setFechaNacimiento(cal);
		expected.setNacionalidad("Uruguayo");
		expected.setDireccion("direccion 1234");
		expected.setPassword("password123");
		expected.setPrimerIngreso(true);
		expected.setDeleted(false);
		
		
	    actual = new Gerente();
	}
	
	@Test
	public void testGerente() {
		actual.setIdUsuario(100);
		actual.setUsername("username");
		actual.setNombre("Nombre");
		actual.setApellido("apellido");
		actual.setCorreo("mail@mail.com");
		
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			cal.setTime(dateFormat.parse("1985-10-05 00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		actual.setFechaNacimiento(cal);
		actual.setNacionalidad("Uruguayo");
		actual.setDireccion("direccion 1234");
		actual.setPassword("password123");
		actual.setPrimerIngreso(true);
		actual.setDeleted(false);
		
		
		assertEquals(expected.getIdUsuario() , actual.getIdUsuario());
		assertEquals(expected.getUsername() , actual.getUsername());
		assertEquals(expected.getPassword() , actual.getPassword());
		assertEquals(expected.getNombre() , actual.getNombre());
		assertEquals(expected.getApellido() , actual.getApellido());
		assertEquals(expected.getFechaNacimiento() , actual.getFechaNacimiento());
		assertEquals(expected.getDireccion() , actual.getDireccion());
		assertEquals(expected.getCorreo() , actual.getCorreo());
		assertEquals(expected.isPrimerIngreso() , actual.isPrimerIngreso());
		assertEquals(expected.isDeleted() , actual.isDeleted());
		
	}

}
