package uy.viruscontrol.test.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uy.viruscontrol.model.dao.impl.FuenteDeDatosDAO;
import uy.viruscontrol.model.entities.FuenteDeDatos;

public class FuenteDeDatosDAOTest {

	@Mock
	private FuenteDeDatosDAO dao;
	
	@Before

    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);

    }
	@Test
	public void testPersist() {
		FuenteDeDatos obj = new FuenteDeDatos();
		dao.persist(obj);
		Mockito.verify(dao).persist(obj);
	}

	@Test
	public void testMerge() {
		FuenteDeDatos obj = new FuenteDeDatos();
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
		dao.findById(100);
		Mockito.verify(dao).findById(100);
	}

	@Test
	public void testDelete() {
		FuenteDeDatos obj = new FuenteDeDatos();
		dao.delete(obj);
		Mockito.verify(dao).delete(obj);
	}


}
