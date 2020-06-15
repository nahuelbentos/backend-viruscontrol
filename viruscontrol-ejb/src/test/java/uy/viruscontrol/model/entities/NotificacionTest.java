package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class NotificacionTest {

	@Mock 
	Notificacion expected, actual;
	@Mock
	Caso caso;
	@Mock
	PlantillaNotificacion plantilla;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
		expected = new Notificacion();
		caso = new Caso();
		plantilla = new PlantillaNotificacion();
		
		expected.setId(100);
		expected.setDescripcion("descripcion");
		expected.setCaso(caso);
		expected.setPlanilla(plantilla);
		
		actual = new Notificacion();
	}
	
	
	@Test
	public void testNotificacionIntStringCasoPlantillaNotificacion() {
		actual.setId(100);
		actual.setDescripcion("descripcion");
		actual.setCaso(caso);
		actual.setPlanilla(plantilla);
		
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getDescripcion(), actual.getDescripcion());
		assertEquals(expected.getCaso(), actual.getCaso());
		assertEquals(expected.getPlanilla(), actual.getPlanilla());
	}

}
