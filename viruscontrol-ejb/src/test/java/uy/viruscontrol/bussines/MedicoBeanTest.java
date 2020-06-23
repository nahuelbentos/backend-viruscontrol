package uy.viruscontrol.bussines;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;



import uy.viruscontrol.bussines.interfaces.MedicoBeanLocal;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorExamenLocal;
import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.CiudadanoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.DepartamentoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.EnfermedadDAOLocal;
import uy.viruscontrol.model.dao.interfaces.ExamenDAOLocal;
import uy.viruscontrol.model.dao.interfaces.MedicoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.ProveedorExamenDAOLocal;
import uy.viruscontrol.model.dao.interfaces.UsuarioDAOLocal;
import uy.viruscontrol.model.dao.interfaces.VisitaMedicoDAOLocal;
import uy.viruscontrol.model.entities.Caso;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Departamento;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.model.entities.Sintoma;
import uy.viruscontrol.model.entities.VisitaMedico;
import uy.viruscontrol.utils.DtEnfermedad;
import uy.viruscontrol.utils.DtExamen;
import uy.viruscontrol.utils.DtProveedorExamen;
import uy.viruscontrol.utils.VisitaPendiente;

public class MedicoBeanTest {
/*
	@Mock
	MedicoBeanLocal Bean;
*/	
	@Mock
	ServiceAgentProveedorExamenLocal saProvEx;
	@Mock
	private CiudadanoDAOLocal ciudadanoDao;
	@Mock 
	private DepartamentoDAOLocal departamentoDao;
	@Mock 
	private ExamenDAOLocal examenDao;
	@Mock 
	private EnfermedadDAOLocal enfermedadDao;
	@Mock 
	private CasoDAOLocal casoDao;
	@Mock 
	private UsuarioDAOLocal usuarioDao;
	@Mock 
	private VisitaMedicoDAOLocal visitaMedicoDao;
	@Mock 
	private ProveedorExamenDAOLocal provExamenDao;
	@Mock 
	private MedicoDAOLocal medicoDao;
	@Mock
	MedicoBean mockedBeanMedico;
	@Mock
	private Medico m;
	
	@InjectMocks
	private MedicoBean Bean;
	
	@Mock
	private DtExamen examenEnfermedad1,examenEnfermedad2;
	@Mock
	private Ciudadano ciudadano1, ciudadano2;
	@Mock
	private ProveedorExamen proExam1, proExam2;
	@Mock
	private VisitaPendiente visitaPendiente1, visitaPendiente2;
	@Mock
	private DtEnfermedad enfermedad1,enfermedad2;
	@Mock
	private Departamento departamento1,departamento2;
	@Mock
	private VisitaMedico visitaMedico1, visitaMedico2;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testObtenerExamenesDeEnfermedad() throws ClientProtocolException, IOException {
		
		examenEnfermedad1 = Mockito.mock(DtExamen.class);
		examenEnfermedad2 = Mockito.mock(DtExamen.class);
		List<DtExamen> examenesEnfermedadesResult = new ArrayList<DtExamen>();
		examenesEnfermedadesResult.add(examenEnfermedad1);
		examenesEnfermedadesResult.add(examenEnfermedad2);
		
		Mockito.when(saProvEx.obtenerExamenesParaUnaEnfermedad(100)).thenReturn(examenesEnfermedadesResult);
		
		List<DtExamen> examenesEnfermedades = new ArrayList<DtExamen>();
		examenesEnfermedades = Bean.obtenerExamenesDeEnfermedad(100);
		
		assertEquals(examenesEnfermedadesResult, examenesEnfermedades);
		
		
	}

	@Test
	public void testMostrarCiudadanos() {
		ciudadano1 =  Mockito.mock(Ciudadano.class);
		ciudadano2 =  Mockito.mock(Ciudadano.class);
		List<Ciudadano> ciudadanosResultList = new ArrayList<Ciudadano>();
		ciudadanosResultList.add(ciudadano1);
		ciudadanosResultList.add(ciudadano2);
		
		Mockito.when(ciudadanoDao.findAll()).thenReturn(ciudadanosResultList);
		
		List<Ciudadano> ciudadanosList = Bean.mostrarCiudadanos();
		
		assertEquals(ciudadanosResultList, ciudadanosList);
		
	}

	@Test
	public void testObtenerProveedoresExamen() {
		
		proExam1 = Mockito.mock(ProveedorExamen.class);
		proExam2 = Mockito.mock(ProveedorExamen.class);
		List<ProveedorExamen> provExamResultList = new ArrayList<ProveedorExamen>();
		provExamResultList.add(proExam1);
		provExamResultList.add(proExam2);
		
		Mockito.when(provExamenDao.findAll()).thenReturn(provExamResultList);
		
		List<DtProveedorExamen> provExamList = Bean.ObtenerProveedoresExamen();
		
		List<ProveedorExamen> finalResultList = new ArrayList<ProveedorExamen>();
		for(DtProveedorExamen dt : provExamList) {
			ProveedorExamen pe = new ProveedorExamen();
			pe.setBarrio(dt.getBarrio());
			pe.setNombre(dt.getNombre());
			pe.setDireccion(dt.getDireccion());
			pe.setId(dt.getId());
			pe.setRangoHorario(dt.getRangoHorario());
			finalResultList.add(pe);
						
		}
		assertEquals(provExamResultList.size(),finalResultList.size());
		
	}

	@Test
	public void testNuevoCaso() {
	
		Mockito.when(mockedBeanMedico.nuevoCaso(100, 100, 100, 100, 100, 100)).thenReturn(true);
		boolean resultadoActual = Bean.nuevoCaso(100, 100, 100, 100, 100, 100);
		
		assertEquals(true, resultadoActual);
	
	
	}

	@Test
	public void testGetVisitaPendiente() {
		
		visitaPendiente1 = Mockito.mock(VisitaPendiente.class);
		/*
		visitaPendiente1.setApellido("ape1");
		visitaPendiente1.setDireccion("dir1");
		visitaPendiente1.setFecha("22/06/2020");
		visitaPendiente1.setId(1);
		visitaPendiente1.setNombre("nom1");
		visitaPendiente1.setSintomas(null);
		*/
		visitaPendiente2 = Mockito.mock(VisitaPendiente.class);
		/*
		visitaPendiente2.setApellido("ape2");
		visitaPendiente2.setDireccion("dir2");
		visitaPendiente2.setFecha("22/06/2020");
		visitaPendiente2.setId(1);
		visitaPendiente2.setNombre("nom2");
		visitaPendiente2.setSintomas(null);
		*/
		List<VisitaPendiente> pendientesResultList = new ArrayList<VisitaPendiente>();
		pendientesResultList.add(visitaPendiente1);
		pendientesResultList.add(visitaPendiente2);
		
		m = Mockito.mock(Medico.class);
		//m.setUsername("edelcovid");
		Mockito.when(usuarioDao.findByUsername("edelcovid")).thenReturn(m);
		/*
		ciudadano1.setNombre("nom1");
		ciudadano1.setApellido("ape1");
		ciudadano1.setDireccion("dir1");
		ciudadano2.setNombre("nom2");
		ciudadano2.setApellido("ape2");
		ciudadano2.setDireccion("dir2");
		*/
		visitaMedico1 = Mockito.mock(VisitaMedico.class);
		/*
		visitaMedico1.setCiudadano(ciudadano1);
		visitaMedico1.setFechaAsignacion(null);
		visitaMedico1.setIdVisitaMedico(1);
		visitaMedico1.setMedico(m);
		visitaMedico1.setSintomas(null);
		visitaMedico1.setVisitaRealizada(false);
		*/
		visitaMedico2 = Mockito.mock(VisitaMedico.class);
		/*
		visitaMedico2.setCiudadano(ciudadano2);
		visitaMedico2.setFechaAsignacion(null);
		visitaMedico2.setIdVisitaMedico(1);
		visitaMedico2.setMedico(m);
		visitaMedico2.setSintomas(null);
		visitaMedico2.setVisitaRealizada(false);
		*/
		
		List<VisitaMedico> visitaMedicoList = new ArrayList<VisitaMedico>();
		visitaMedicoList.add(visitaMedico1);
		visitaMedicoList.add(visitaMedico2);
		Mockito.when(visitaMedicoDao.findByMedico(m)).thenReturn(visitaMedicoList);
		
		Mockito.when(mockedBeanMedico.getVisitaPendiente("edelcovid")).thenReturn(pendientesResultList);
		List<VisitaPendiente> pendientesList = new ArrayList<VisitaPendiente>();
		pendientesList = Bean.getVisitaPendiente("edelcovid");
		
		
		//assertEquals(pendientesResultList.size(), visitaMedicoList.size());
		//Mockito.verify(mockedBeanMedico).getVisitaPendiente("mediUser");
	}


	@Test
	public void testConfirmarVisitaPendiente() {
		/*
		visitaMedico1 = Mockito.mock(VisitaMedico.class);
		visitaMedico1.setCiudadano(ciudadano1);
		visitaMedico1.setFechaAsignacion(null);
		visitaMedico1.setIdVisitaMedico(1);
		visitaMedico1.setMedico(m);
		visitaMedico1.setSintomas(null);
		visitaMedico1.setVisitaRealizada(false);
		
		Mockito.when(visitaMedicoDao.findById(100)).thenReturn(visitaMedico1);
		
		Mockito.when(mockedBeanMedico.confirmarVisitaPendiente(visitaMedico1.getMedico().getUsername(), visitaMedico1.getIdVisitaMedico())).thenReturn(true);
		
		boolean resultadoActual = Bean.confirmarVisitaPendiente(visitaMedico1.getMedico().getUsername(), visitaMedico1.getIdVisitaMedico());
		
		assertEquals(true, resultadoActual);
	//	Mockito.verify(Bean).confirmarVisitaPendiente("username", 100);
	 * */
		/*
		Mockito.when(visitaMedicoDao.findById(100)).thenReturn(visitaMedico1);
		
		MedicoBean beanMedico = Mockito.mock(MedicoBean.class);
		Mockito.when(beanMedico.confirmarVisitaPendiente("medUser", 100)).thenReturn(true);
		//boolean resultadoActual = Bean.confirmarVisitaPendiente("medUser", 100);
		*/
		boolean resultadoActual = Bean.confirmarVisitaPendiente("medUser", 100);
		assertEquals(false, resultadoActual);
		
		Mockito.verify(visitaMedicoDao, Mockito.times(1)).findById(100);
		//Mockito.verify(visitaMedicoDao, Mockito.times(1)).merge(visitaMedico1);
		
	}

	@Test
	public void testEnfermerdadesAprobadas() {
		enfermedad1 = Mockito.mock(DtEnfermedad.class);
		enfermedad2 = Mockito.mock(DtEnfermedad.class);
		List<DtEnfermedad> enfermedadesResultList = new ArrayList<DtEnfermedad>();
		enfermedadesResultList.add(enfermedad1);
		enfermedadesResultList.add(enfermedad2);
		
		//Mockito.when(mockedBeanMedico.enfermerdadesAprobadas()).thenReturn(enfermedadesResultList);
	//	List<DtEnfermedad> enfermedadesList = Bean.enfermerdadesAprobadas();
		
		assertEquals(enfermedadesResultList, Bean.enfermerdadesAprobadas());
		//Mockito.verify(Bean).enfermerdadesAprobadas();
		
	}

	@Test
	public void testExamenesEnfermedad() {
		examenEnfermedad1 = Mockito.mock(DtExamen.class);
		examenEnfermedad2 = Mockito.mock(DtExamen.class);
		List<DtExamen> examenesEnfermedadesResult = new ArrayList<DtExamen>();
		examenesEnfermedadesResult.add(examenEnfermedad1);
		examenesEnfermedadesResult.add(examenEnfermedad2);
		
		Mockito.when(Bean.examenesEnfermedad(100)).thenReturn(examenesEnfermedadesResult);
		List<DtExamen> examenesEnfermedades = Bean.examenesEnfermedad(100);
		
		assertEquals(examenesEnfermedadesResult, examenesEnfermedades);
		Mockito.verify(Bean).examenesEnfermedad(100);
		
		
	}

	@Test
	public void testObtenerDepartamentos() {
		departamento1 = Mockito.mock(Departamento.class);
		departamento2 = Mockito.mock(Departamento.class);
		List<Departamento> departamentosResultList = new ArrayList<Departamento>();
		departamentosResultList.add(departamento1);
		departamentosResultList.add(departamento2);
		
		Mockito.when(Bean.obtenerDepartamentos()).thenReturn(departamentosResultList);
		List<Departamento> deptosList = Bean.obtenerDepartamentos();
		
		assertEquals(departamentosResultList, deptosList);
		Mockito.verify(Bean).obtenerDepartamentos();
		
	}

}
