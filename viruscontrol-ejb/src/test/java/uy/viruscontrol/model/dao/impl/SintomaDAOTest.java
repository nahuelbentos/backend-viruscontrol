package uy.viruscontrol.model.dao.impl;


import org.junit.Test;


import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import org.junit.*;

import uy.viruscontrol.model.dao.impl.SintomaDAO;
import uy.viruscontrol.model.entities.Sintoma;



public class SintomaDAOTest {

	@Mock
	private SintomaDAO dao;
	/*
	@InjectMocks
	private EnfermedadBean bean;
	*/
	
    @Before

    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);

    }
	
	@Test
	public void inDAOSintoma_PersistMethodIsInvoked() {
		
		Sintoma s = new Sintoma();
		dao.persist(s);
		Mockito.verify(dao).persist(s);
	}
	
	@Test
	public void inDAOSintoma_MergeMethodIsInvoked() {
		Sintoma s = new Sintoma();
		dao.merge(s);
		Mockito.verify(dao).merge(s);
		
	}
	
	@Test
	public void inDAOSintoma_FindAllMethodIsInvoked() {
		
		dao.findAll();
		Mockito.verify(dao).findAll();
	}
	
	@Test
	public void inDAOSintoma_FindByMethodIsInvoked() {
		
		dao.findById(100);
		Mockito.verify(dao).findById(100);
	}
	
	@Test
	public void inDAOSintoma_ExistMethodIsInvoked() {
		
		dao.exist("Cefalea");
		Mockito.verify(dao).exist("Cefalea");
	}
	
	@Test
	public void inDAOSintoma_DeleteMethodIsInvoked() {
		Sintoma s = new Sintoma();
		dao.delete(s);
		Mockito.verify(dao).delete(s);
	}
	
}
