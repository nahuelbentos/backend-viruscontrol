package uy.viruscontrol.bussines;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uy.viruscontrol.bussines.interfaces.EnfermedadBeanLocal;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.Sintoma;
import uy.viruscontrol.model.entities.TipoRecurso;

public class EnfermedadBeanTest {

	
	@Mock
	EnfermedadBeanLocal mockedBeanLocalEnfermedad;
	@Mock 
	Sintoma mockedSintoma1, mockedSintoma2;
	@Mock
	List<Recurso> mockedRecursosList;
	@Mock
	TipoRecurso mockedTipoRecurso;
	@Mock
	List<Sintoma> mockedSintomasList;
	@Mock
	List<Enfermedad> mockedEnfermedadesList;
	@Mock
	List<TipoRecurso> mockedTipoRecursoList;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void testAsociarRecursoRecomendado() {
		//asociarRecursoRecomendado(String nombreEnfermedad, String nombreRecurso, boolean recursoTrata, boolean recursoPreviene) {
		boolean recursoTrata = false, recursoPreviene = false;
		mockedBeanLocalEnfermedad.asociarRecursoRecomendado("enfermedadTest", "recursoTest", recursoTrata, recursoPreviene);
		
		Mockito.verify(mockedBeanLocalEnfermedad).asociarRecursoRecomendado("enfermedadTest", "recursoTest", recursoTrata, recursoPreviene);
		
	}

	@Test
	public void testCrearEnfermedadInfecciosa() {
		//(String nombreEnfermedad, String nombreTipoEnfermedad, 
		//String nombreAgente, List<Sintoma> sintomas, boolean aprobada, float distanciaContagio)
		List<Sintoma> sintomas = new ArrayList<Sintoma>();
		mockedSintoma1.setNombre("sintoma 1");
		mockedSintoma2.setNombre("Sintoma 2");
		sintomas.add(mockedSintoma1);
		sintomas.add(mockedSintoma2);
		float distancia = 1;
		boolean aprobada=false;
		
		mockedBeanLocalEnfermedad.crearEnfermedadInfecciosa("enfermedadTest", "tipoEnfermedadTest", "agenteTest", sintomas,
				aprobada, distancia);
		
		Mockito.verify(mockedBeanLocalEnfermedad).crearEnfermedadInfecciosa("enfermedadTest", "tipoEnfermedadTest", "agenteTest", sintomas,
				aprobada, distancia);
		
	}

	@Test
	public void testAprobarEnfermedadInfecciosa() {
		mockedBeanLocalEnfermedad.aprobarEnfermedadInfecciosa(100);
		Mockito.verify(mockedBeanLocalEnfermedad).aprobarEnfermedadInfecciosa(100);
	}

	@Test
	public void testRechazarEnfermedadInfecciosa() {
		mockedBeanLocalEnfermedad.rechazarEnfermedadInfecciosa(100);
		Mockito.verify(mockedBeanLocalEnfermedad).rechazarEnfermedadInfecciosa(100);
	}

	@Test
	public void testAltaRecursoDeUnDeterminadoTipo() {
		mockedBeanLocalEnfermedad.altaRecursoDeUnDeterminadoTipo("nombreTipoTest", 100, "codigoTest");
		Mockito.verify(mockedBeanLocalEnfermedad).altaRecursoDeUnDeterminadoTipo("nombreTipoTest", 100, "codigoTest");
	}

	@Test
	public void testAltaTipoRecurso() {
		mockedBeanLocalEnfermedad.altaTipoRecurso("nombreTipoTest", "descripcionTipoRecursoTest", "codigoTest");
		Mockito.verify(mockedBeanLocalEnfermedad).altaTipoRecurso("nombreTipoTest", "descripcionTipoRecursoTest", "codigoTest");
	
	}

	@Test
	public void testObtenerRecursosPorTipoRecurso() {
		mockedRecursosList = mockedBeanLocalEnfermedad.obtenerRecursosPorTipoRecurso(mockedTipoRecurso);
		Mockito.verify(mockedBeanLocalEnfermedad).obtenerRecursosPorTipoRecurso(mockedTipoRecurso);
	}

	@Test
	public void testObtenerRecursosDisponibles() {
		mockedRecursosList = mockedBeanLocalEnfermedad.obtenerRecursosDisponibles();
		Mockito.verify(mockedBeanLocalEnfermedad).obtenerRecursosDisponibles();
	
	}

	@Test
	public void testObtenerRecursoPorEnfermedad() {
		mockedRecursosList = mockedBeanLocalEnfermedad.obtenerRecursoPorEnfermedad(100);
		Mockito.verify(mockedBeanLocalEnfermedad).obtenerRecursoPorEnfermedad(100);
	
	}

	@Test
	public void testGetIdEnfermedadByName() {
		mockedBeanLocalEnfermedad.getIdEnfermedadByName("nombreTest");
		Mockito.verify(mockedBeanLocalEnfermedad).getIdEnfermedadByName("nombreTest");
	
	}

	@Test
	public void testGetIdRecursoByName() {
		mockedBeanLocalEnfermedad.getIdRecursoByName("nombreTest");
		Mockito.verify(mockedBeanLocalEnfermedad).getIdRecursoByName("nombreTest");
	
	}

	@Test
	public void testGetIdTipoByName() {
		mockedBeanLocalEnfermedad.getIdTipoByName("nombreTest");
		Mockito.verify(mockedBeanLocalEnfermedad).getIdTipoByName("nombreTest");
	
	}

	@Test
	public void testGetIdSintomaByName() {
		mockedBeanLocalEnfermedad.getIdSintomaByName("nombreTest");
		Mockito.verify(mockedBeanLocalEnfermedad).getIdSintomaByName("nombreTest");
	}

	@Test
	public void testGetIdTipoRecursoByName() {
		mockedBeanLocalEnfermedad.getIdTipoRecursoByName("nombreTest");
		Mockito.verify(mockedBeanLocalEnfermedad).getIdTipoRecursoByName("nombreTest");
	}

	@Test
	public void testObtenerListaSintomas() {
		mockedSintomasList = mockedBeanLocalEnfermedad.obtenerListaSintomas();
		Mockito.verify(mockedBeanLocalEnfermedad).obtenerListaSintomas();
	}

	@Test
	public void testObtenerListaEnfermedadesNoAprobadas() {
		mockedEnfermedadesList = mockedBeanLocalEnfermedad.obtenerListaEnfermedadesNoAprobadas();
		Mockito.verify(mockedBeanLocalEnfermedad).obtenerListaEnfermedadesNoAprobadas();
	}

	@Test
	public void testObtenerTiposDeRecursos() {
		mockedTipoRecursoList = mockedBeanLocalEnfermedad.obtenerTiposDeRecursos();
		Mockito.verify(mockedBeanLocalEnfermedad).obtenerTiposDeRecursos();
	}

	@Test
	public void testObtenerRecursos() {
		mockedRecursosList = mockedBeanLocalEnfermedad.obtenerRecursos();
		Mockito.verify(mockedBeanLocalEnfermedad).obtenerRecursos();
	}

	@Test
	public void testObtenerEnfermedades() {
		mockedEnfermedadesList = mockedBeanLocalEnfermedad.obtenerEnfermedades();
		Mockito.verify(mockedBeanLocalEnfermedad).obtenerEnfermedades();
	}

}
