package uy.viruscontrol.model.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uy.viruscontrol.bussines.enumerated.TipoCaso;

public class CasoTest {

	
	
	@Mock
	Departamento departamento;
	@Mock
	Examen examen;
	@Mock 
	Enfermedad enfermedad;
	@Mock 
	Caso caso;
	
	
	
	@Before

    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);
         departamento = new Departamento("Montevideo");
         examen = new Examen(enfermedad);
         enfermedad = new Enfermedad("Enfermedad Test", false, null, null, null,false);
         caso = new Caso(1, TipoCaso.CONFIRMADO, departamento, examen, enfermedad);
         
    }
	
	@Test
	public void testCaso() {
		Caso actual = new Caso();
		assertEquals(caso.getClass(), actual.getClass());
		
	}

	@Test
	public void testCasoIntTipoCasoDepartamentoExamenEnfermedad() {
		Caso actual = new Caso(1, TipoCaso.CONFIRMADO, departamento, examen, enfermedad);
		assertEquals(caso.getClass(), actual.getClass());
	}

	@Test
	public void testGetId() {
		assertEquals(1,caso.getId());
	}

	@Test
	public void testSetId() {
		caso.setId(2);
		assertEquals(2,caso.getId());
	}

	@Test
	public void testGetTipoCaso() {
		assertEquals(TipoCaso.CONFIRMADO,caso.getTipoCaso());
	}

	@Test
	public void testSetTipoCaso() {
		caso.setTipoCaso(TipoCaso.SOSPECHOSO);
		assertEquals(TipoCaso.SOSPECHOSO,caso.getTipoCaso());
	}
	
	@Test
	public void testGetDepartamento() {
		assertEquals("Montevideo",caso.getDepartamento().getNombre());
		
	}

	@Test
	public void testSetDepartamento() {
		caso.setDepartamento(departamento);
		assertEquals(departamento.getNombre(),caso.getDepartamento().getNombre());
	}
	
	@Test
	public void testGetExamen() {
		assertEquals(examen.getEnfermedad(),caso.getExamen().getEnfermedad());
	}

	@Test
	public void testSetExamen() {
		caso.setExamen(examen);
		assertEquals(examen.getEnfermedad(),caso.getExamen().getEnfermedad());
	}

	@Test
	public void testGetEnfermedad() {
		assertEquals(enfermedad.getNombre(),caso.getEnfermedad().getNombre());
	}

	@Test
	public void testSetEnfermedad() {
		caso.setEnfermedad(enfermedad);
		assertEquals(enfermedad.getNombre(),caso.getEnfermedad().getNombre());
	}

}
