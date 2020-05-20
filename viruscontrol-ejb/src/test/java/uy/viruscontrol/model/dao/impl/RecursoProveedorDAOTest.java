package uy.viruscontrol.model.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uy.viruscontrol.model.dao.impl.RecursoProveedorDAO;
import uy.viruscontrol.model.entities.IdRecursoProveedor;
import uy.viruscontrol.model.entities.RecursoProveedor;



public class RecursoProveedorDAOTest {

	@Mock
	private RecursoProveedorDAO dao;
	
	@Before

    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);

    }
	@Test
	public void testPersist() {
		RecursoProveedor obj = new RecursoProveedor();
		dao.persist(obj);
		Mockito.verify(dao).persist(obj);
	}

	@Test
	public void testMerge() {
		RecursoProveedor obj = new RecursoProveedor();
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
		IdRecursoProveedor id = new IdRecursoProveedor();
		dao.findById(id);
		Mockito.verify(dao).findById(id);
	}

	@Test
	public void testDelete() {
		IdRecursoProveedor obj = new IdRecursoProveedor();
		dao.delete(obj);
		Mockito.verify(dao).delete(obj);
	}

}
