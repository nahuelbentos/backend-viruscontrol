package uy.viruscontrol.model.dao.impl;


import org.junit.Test;


import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import org.junit.*;

import uy.viruscontrol.model.dao.impl.RecursoDAO;
import uy.viruscontrol.model.entities.Recurso;




public class RecursoDAOTest {

	@Mock
	private RecursoDAO dao;
	/*
	@InjectMocks
	private EnfermedadBean bean;
	*/
	
    @Before

    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);

    }
	
	@Test
	public void inDAORecurso_PersistMethodIsInvoked() {
		
		Recurso r = new Recurso();
		dao.persist(r);
		Mockito.verify(dao).persist(r);
		
	}
	
	@Test
	public void inDAORecurso_MergeMethodIsInvoked() {
		Recurso r = new Recurso();
		dao.merge(r);
		Mockito.verify(dao).merge(r);
		
	}
	
	@Test
	public void inDAORecurso_FindAllMethodIsInvoked() {
		
		dao.findAll();
		Mockito.verify(dao).findAll();
	}
	
	@Test
	public void inDAORecurso_FindByMethodIsInvoked() {
		
		dao.findById(100);
		Mockito.verify(dao).findById(100);
	}
	
	@Test
	public void inDAORecurso_ExistMethodIsInvoked() {
		
		dao.exist("Paracetamol");
		Mockito.verify(dao).exist("Paracetamol");
	}
	
	@Test
	public void inDAORecurso_DeleteMethodIsInvoked() {
		Recurso r = new Recurso();
		dao.delete(r);
		Mockito.verify(dao).delete(r);
	}
	
}
