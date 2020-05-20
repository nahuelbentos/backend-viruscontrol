package uy.viruscontrol.model.dao.impl;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uy.viruscontrol.model.dao.impl.DepartamentoDAO;
import uy.viruscontrol.model.entities.Departamento;

public class DepartamentoDAOTest {

	@Mock
	private DepartamentoDAO dao;
	
	@Before

    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);

    }
	@Test
	public void testPersist() {
		Departamento obj = new Departamento();
		dao.persist(obj);
		Mockito.verify(dao).persist(obj);
	}

	@Test
	public void testMerge() {
		Departamento obj = new Departamento();
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
		Departamento obj = new Departamento();
		dao.delete(obj);
		Mockito.verify(dao).delete(obj);
	}


}
