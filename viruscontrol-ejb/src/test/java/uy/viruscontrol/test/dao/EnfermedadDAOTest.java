package uy.viruscontrol.test.dao;


import org.junit.Test;


import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import org.junit.*;

import uy.viruscontrol.model.dao.impl.EnfermedadDAO;
import uy.viruscontrol.model.entities.Enfermedad;



public class EnfermedadDAOTest {

	@Mock
	private EnfermedadDAO daoEnfermedad;
	/*
	@InjectMocks
	private EnfermedadBean bean;
	*/
	
    @Before

    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);

    }
	
	@Test
	public void inDAOEnfermedad_PersistMethodIsInvoked() {
		
		Enfermedad e = new Enfermedad();
		daoEnfermedad.persist(e);
		Mockito.verify(daoEnfermedad).persist(e);
				
		//Mockito.when(daoEnfermedad.persist(e)).thenReturn(true);
		//assertEquals(e.getNombre(), dao.findById(bean.getIdEnfermedadByName("Enfermedad PUnit")).getNombre());
		//e = dao.findById(bean.getIdEnfermedadByName("Enfermedad PUnit"));
		//Assert.assertEquals(e.getNombre(), "Enfermedad PUnit");
	}
	
	@Test
	public void inDAOEnfermedad_MergeMethodIsInvoked() {
		Enfermedad e = new Enfermedad();
		daoEnfermedad.merge(e);
		Mockito.verify(daoEnfermedad).merge(e);
		
	}
	
	@Test
	public void inDAOEnfermedad_FindAllMethodIsInvoked() {
		
		daoEnfermedad.findAll();
		Mockito.verify(daoEnfermedad).findAll();
	}
	
	@Test
	public void inDAOEnfermedad_FindByMethodIsInvoked() {
		
		daoEnfermedad.findById(100);
		Mockito.verify(daoEnfermedad).findById(100);
	}
	
	@Test
	public void inDAOEnfermedad_ExistMethodIsInvoked() {
		
		daoEnfermedad.exist("COVID-19");
		Mockito.verify(daoEnfermedad).exist("COVID-19");
	}
	
	@Test
	public void inDAOEnfermedad_DeleteMethodIsInvoked() {
		Enfermedad e = new Enfermedad();
		daoEnfermedad.delete(e);
		Mockito.verify(daoEnfermedad).delete(e);
	}
	
}
