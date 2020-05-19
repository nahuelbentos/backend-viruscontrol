package uy.viruscontrol.test.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uy.viruscontrol.model.dao.impl.RecursoEnfermedadDAO;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.RecursoEnfermedad;



public class RecursoEnfermedadDAOTest {

	@Mock
	private RecursoEnfermedadDAO dao;
	
	@Before

    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);

    }
	@Test
	public void testPersist() {
		RecursoEnfermedad obj = new RecursoEnfermedad();
		dao.persist(obj);
		Mockito.verify(dao).persist(obj);
	}

	@Test
	public void testMerge() {
		RecursoEnfermedad obj = new RecursoEnfermedad();
		dao.merge(obj);
		Mockito.verify(dao).merge(obj);
	}

	@Test
	public void testFindAll() {
		dao.findAll();
		Mockito.verify(dao).findAll();
	}

	@Test
	public void testFindById() {
		Recurso r = new Recurso();
		Enfermedad e = new Enfermedad();
		dao.findById(r,e);
		Mockito.verify(dao).findById(r,e);
	}

	@Test
	public void testDelete() {
		RecursoEnfermedad obj = new RecursoEnfermedad();
		dao.delete(obj);
		Mockito.verify(dao).delete(obj);
	}

}
