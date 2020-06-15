package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class VisitaMedicoTest {

	@Mock
	VisitaMedico expected, actual;
	@Mock
	Ciudadano ciudadano;
	@Mock
	Medico medico;
	@Mock 
	Sintoma sintoma1, sintoma2;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		cal.setTime(dateFormat.parse("1985-10-05 00:00"));
		
		expected = new VisitaMedico();
		expected.setCiudadano(ciudadano);
		expected.setMedico(medico);
		expected.setIdVisitaMedico(100);
		expected.setFechaAsignacion(cal);
		expected.setVisitaRealizada(true);
		
		sintoma1 = new Sintoma();
		sintoma2 = new Sintoma();
		List<Sintoma> sintomas = new ArrayList<Sintoma>();
		sintomas.add(sintoma1);
		sintomas.add(sintoma2);
		expected.setSintomas(sintomas);
		
		actual = new VisitaMedico();
	}
	
	@Test
	public void testVisitaMedicoMedicoCiudadanoCalendarBoolean() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			cal.setTime(dateFormat.parse("1985-10-05 00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		actual.setCiudadano(ciudadano);
		actual.setMedico(medico);
		actual.setIdVisitaMedico(100);
		actual.setFechaAsignacion(cal);
		actual.setVisitaRealizada(true);
		
		sintoma1 = new Sintoma();
		sintoma2 = new Sintoma();
		List<Sintoma> sintomas = new ArrayList<Sintoma>();
		sintomas.add(sintoma1);
		sintomas.add(sintoma2);
		actual.setSintomas(sintomas);
		
		assertEquals(expected.getCiudadano(), actual.getCiudadano());
		assertEquals(expected.getMedico(), actual.getMedico());
		assertEquals(expected.getFechaAsignacion(), actual.getFechaAsignacion());
		assertEquals(expected.getIdVisitaMedico(), actual.getIdVisitaMedico());
		assertEquals(expected.isVisitaRealizada(), actual.isVisitaRealizada());
		assertEquals(expected.getSintomas().get(0).getNombre(), actual.getSintomas().get(0).getNombre());
	}

}
