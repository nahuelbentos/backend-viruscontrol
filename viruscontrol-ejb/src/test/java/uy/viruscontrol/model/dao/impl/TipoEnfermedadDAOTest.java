package uy.viruscontrol.model.dao.impl;


import org.junit.Test;


import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import org.junit.*;

import uy.viruscontrol.model.dao.impl.TipoEnfermedadDAO;
import uy.viruscontrol.model.entities.TipoEnfermedad;



public class TipoEnfermedadDAOTest {

	@Mock
	private TipoEnfermedadDAO daoTipoEnfermedad;
	/*
	@InjectMocks
	private EnfermedadBean bean;
	*/
	
    @Before

    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);

    }
	
	@Test
	public void inDAOTipoEnfermedad_PersistMethodIsInvoked() {
		
		TipoEnfermedad te = new TipoEnfermedad();
		daoTipoEnfermedad.persist(te);
		Mockito.verify(daoTipoEnfermedad).persist(te);
		
	}
	
	@Test
	public void inDAOTipoEnfermedad_MergeMethodIsInvoked() {
		TipoEnfermedad te = new TipoEnfermedad();
		daoTipoEnfermedad.merge(te);
		Mockito.verify(daoTipoEnfermedad).merge(te);
		
	}
	
	@Test
	public void inDAOTipoEnfermedad_FindAllMethodIsInvoked() {
		
		daoTipoEnfermedad.findAll();
		Mockito.verify(daoTipoEnfermedad).findAll();
	}
	
	@Test
	public void inDAOTipoEnfermedad_FindByMethodIsInvoked() {
		
		daoTipoEnfermedad.findById(100);
		Mockito.verify(daoTipoEnfermedad).findById(100);
	}
	
	@Test
	public void inDAOTipoEnfermedad_ExistMethodIsInvoked() {
	
		daoTipoEnfermedad.exist("Virus");
		Mockito.verify(daoTipoEnfermedad).exist("Virus");
	}
	
	@Test
	public void inDAOTipoEnfermedad_DeleteMethodIsInvoked() {
		TipoEnfermedad te = new TipoEnfermedad();
		daoTipoEnfermedad.delete(te);
		Mockito.verify(daoTipoEnfermedad).delete(te);
	}
	
}
