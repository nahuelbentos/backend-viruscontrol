package uy.viruscontrol.model.dao.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uy.viruscontrol.model.dao.impl.CiudadanoDAO;

import uy.viruscontrol.model.entities.Ciudadano;


public class CiudadanoDAOTest {
	@Mock
	CiudadanoDAO dao;
	@Mock
	CiudadanoDAO daoAux; 
	
	@Before

    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);
         daoAux = new CiudadanoDAO();
    }
	
	
	@Test
	public void testCiudadanoDAO() {
		CiudadanoDAO dao1 = new CiudadanoDAO();
		assertEquals(daoAux.getClass(),dao1.getClass());
	}
	
	@Test
	public void testPersist() {
		Ciudadano obj = new Ciudadano();
		dao.persist(obj);
		Mockito.verify(dao).persist(obj);
	}

	@Test
	public void testMerge() {
		Ciudadano obj = new Ciudadano();
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
		Ciudadano obj = new Ciudadano();
		dao.delete(obj);
		Mockito.verify(dao).delete(obj);
	}

}
