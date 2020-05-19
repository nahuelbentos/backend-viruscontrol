package uy.viruscontrol.test.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uy.viruscontrol.model.dao.impl.ProveedorExamenDAO;
import uy.viruscontrol.model.entities.ProveedorExamen;

public class ProveedorExamenDAOTest {

	@Mock
	private ProveedorExamenDAO dao;
	
	@Before

    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);

    }
	@Test
	public void testPersist() {
		ProveedorExamen obj = new ProveedorExamen();
		dao.persist(obj);
		Mockito.verify(dao).persist(obj);
	}

	@Test
	public void testMerge() {
		ProveedorExamen obj = new ProveedorExamen();
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
		ProveedorExamen obj = new ProveedorExamen();
		dao.delete(obj);
		Mockito.verify(dao).delete(obj);
	}

}
