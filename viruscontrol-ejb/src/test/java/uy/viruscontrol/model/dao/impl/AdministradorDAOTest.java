package uy.viruscontrol.model.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uy.viruscontrol.model.dao.impl.AdministradorDAO;
import uy.viruscontrol.model.entities.Administrador;

public class AdministradorDAOTest {
	
	@Mock
	private AdministradorDAO dao;
	
	@Before

    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);

    }
	
	
	@Test
	public void testPersist() {
		Administrador a = new Administrador();
		dao.persist(a);
		Mockito.verify(dao).persist(a);
	}

	@Test
	public void testMerge() {
		Administrador a = new Administrador();
		dao.merge(a);
		Mockito.verify(dao).merge(a);
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
		Administrador a = new Administrador();
		dao.delete(a);
		Mockito.verify(dao).delete(a);
	}

}
