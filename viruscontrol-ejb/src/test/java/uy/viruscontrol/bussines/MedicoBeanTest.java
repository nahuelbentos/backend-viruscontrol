package uy.viruscontrol.bussines;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uy.viruscontrol.bussines.interfaces.MedicoBeanLocal;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Departamento;
import uy.viruscontrol.utils.DtEnfermedad;
import uy.viruscontrol.utils.DtExamen;
import uy.viruscontrol.utils.DtProveedorExamen;
import uy.viruscontrol.utils.VisitaPendiente;

public class MedicoBeanTest {

	@Mock
	MedicoBeanLocal mockedBean;
	
	private DtExamen examenEnfermedad1;
	private DtExamen examenEnfermedad2;
	private Ciudadano ciudadano1;
	private Ciudadano ciudadano2;
	private DtProveedorExamen proExam1;
	private DtProveedorExamen proExam2;
	private VisitaPendiente visitaPendiente1;
	private VisitaPendiente visitaPendiente2;
	private DtEnfermedad enfermedad1;
	private DtEnfermedad enfermedad2;
	private Departamento departamento1;
	private Departamento departamento2;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testObtenerExamenesDeEnfermedad() {
		
		examenEnfermedad1 = Mockito.mock(DtExamen.class);
		examenEnfermedad2 = Mockito.mock(DtExamen.class);
		List<DtExamen> examenesEnfermedadesResult = new ArrayList<DtExamen>();
		examenesEnfermedadesResult.add(examenEnfermedad1);
		examenesEnfermedadesResult.add(examenEnfermedad2);
		
		Mockito.when(mockedBean.obtenerExamenesDeEnfermedad(100)).thenReturn(examenesEnfermedadesResult);
		
		List<DtExamen> examenesEnfermedades = new ArrayList<DtExamen>();
		examenesEnfermedades = mockedBean.obtenerExamenesDeEnfermedad(100);
		
		assertEquals(examenesEnfermedadesResult, examenesEnfermedades);
		Mockito.verify(mockedBean).obtenerExamenesDeEnfermedad(100);
		
	}

	@Test
	public void testMostrarCiudadanos() {
		ciudadano1 =  Mockito.mock(Ciudadano.class);
		ciudadano2 =  Mockito.mock(Ciudadano.class);
		List<Ciudadano> ciudadanosResultList = new ArrayList<Ciudadano>();
		ciudadanosResultList.add(ciudadano1);
		ciudadanosResultList.add(ciudadano2);
		
		Mockito.when(mockedBean.mostrarCiudadanos()).thenReturn(ciudadanosResultList);
		
		List<Ciudadano> ciudadanosList = mockedBean.mostrarCiudadanos();
		
		assertEquals(ciudadanosResultList, ciudadanosList);
		Mockito.verify(mockedBean).mostrarCiudadanos();
	}

	@Test
	public void testObtenerProveedoresExamen() {
		
		proExam1 = Mockito.mock(DtProveedorExamen.class);
		proExam2 = Mockito.mock(DtProveedorExamen.class);
		List<DtProveedorExamen> provExamResultList = new ArrayList<DtProveedorExamen>();
		provExamResultList.add(proExam1);
		provExamResultList.add(proExam2);
		
		Mockito.when(mockedBean.ObtenerProveedoresExamen()).thenReturn(provExamResultList);
		
		List<DtProveedorExamen> provExamList = mockedBean.ObtenerProveedoresExamen();
		
		assertEquals(provExamResultList,provExamList);
		Mockito.verify(mockedBean).ObtenerProveedoresExamen();
	}

	@Test
	public void testNuevoCaso() {
		
		Mockito.when(mockedBean.nuevoCaso(100, 100, 100, 100, 100, 100)).thenReturn(true);
		boolean resultadoActual = mockedBean.nuevoCaso(100, 100, 100, 100, 100, 100);
		
		assertEquals(true, resultadoActual);
		Mockito.verify(mockedBean).nuevoCaso(100,100,100,100,100,100);
	}

	@Test
	public void testGetVisitaPendiente() {
		visitaPendiente1 = Mockito.mock(VisitaPendiente.class);
		visitaPendiente2 = Mockito.mock(VisitaPendiente.class);
		List<VisitaPendiente> pendientesResultList = new ArrayList<VisitaPendiente>();
		pendientesResultList.add(visitaPendiente1);
		pendientesResultList.add(visitaPendiente2);
		
		Mockito.when(mockedBean.getVisitaPendiente("username")).thenReturn(pendientesResultList);
		
		List<VisitaPendiente> pendientesList = mockedBean.getVisitaPendiente("username");
		
		assertEquals(pendientesResultList, pendientesList);
		Mockito.verify(mockedBean).getVisitaPendiente("username");
	}

	@Test
	public void testConfirmarVisitaPendiente() {
		Mockito.when(mockedBean.confirmarVisitaPendiente("username", 100)).thenReturn(true);
		
		boolean resultadoActual = mockedBean.confirmarVisitaPendiente("username", 100);
		
		assertEquals(true, resultadoActual);
		Mockito.verify(mockedBean).confirmarVisitaPendiente("username", 100);
		
	}

	@Test
	public void testEnfermerdadesAprobadas() {
		enfermedad1 = Mockito.mock(DtEnfermedad.class);
		enfermedad2 = Mockito.mock(DtEnfermedad.class);
		List<DtEnfermedad> enfermedadesResultList = new ArrayList<DtEnfermedad>();
		enfermedadesResultList.add(enfermedad1);
		enfermedadesResultList.add(enfermedad2);
		
		Mockito.when(mockedBean.enfermerdadesAprobadas()).thenReturn(enfermedadesResultList);
		List<DtEnfermedad> enfermedadesList = mockedBean.enfermerdadesAprobadas();
		
		assertEquals(enfermedadesResultList, enfermedadesList);
		Mockito.verify(mockedBean).enfermerdadesAprobadas();
		
	}

	@Test
	public void testExamenesEnfermedad() {
		examenEnfermedad1 = Mockito.mock(DtExamen.class);
		examenEnfermedad2 = Mockito.mock(DtExamen.class);
		List<DtExamen> examenesEnfermedadesResult = new ArrayList<DtExamen>();
		examenesEnfermedadesResult.add(examenEnfermedad1);
		examenesEnfermedadesResult.add(examenEnfermedad2);
		
		Mockito.when(mockedBean.examenesEnfermedad(100)).thenReturn(examenesEnfermedadesResult);
		List<DtExamen> examenesEnfermedades = mockedBean.examenesEnfermedad(100);
		
		assertEquals(examenesEnfermedadesResult, examenesEnfermedades);
		Mockito.verify(mockedBean).examenesEnfermedad(100);
		
		
	}

	@Test
	public void testObtenerDepartamentos() {
		departamento1 = Mockito.mock(Departamento.class);
		departamento2 = Mockito.mock(Departamento.class);
		List<Departamento> departamentosResultList = new ArrayList<Departamento>();
		departamentosResultList.add(departamento1);
		departamentosResultList.add(departamento2);
		
		Mockito.when(mockedBean.obtenerDepartamentos()).thenReturn(departamentosResultList);
		List<Departamento> deptosList = mockedBean.obtenerDepartamentos();
		
		assertEquals(departamentosResultList, deptosList);
		Mockito.verify(mockedBean).obtenerDepartamentos();
		
	}

}
