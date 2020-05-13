package uy.viruscontrol.test.dao;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import uy.viruscontrol.model.dao.impl.TipoEnfermedadDAO;
import uy.viruscontrol.model.dao.interfaces.TipoEnfermedadDAOLocal;
import uy.viruscontrol.model.entities.TipoEnfermedad;


@RunWith(Arquillian.class)
public class TipoEnfermedadDAOTest{
	
	@Deployment
	public static JavaArchive createDeployment() {
	    return ShrinkWrap.create(JavaArchive.class)
	      .addClasses(TipoEnfermedad.class, TipoEnfermedadDAO.class, TipoEnfermedadDAOLocal.class)
	      .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	private TipoEnfermedadDAOLocal dao;
	
	@Test
	public void persistir() {
		
		assertTrue(dao.findAll().isEmpty());
		
		TipoEnfermedad te1 = new TipoEnfermedad();
		te1.setNombre("TipoEnfermedad Test 1");
		
		TipoEnfermedad te2 = new TipoEnfermedad();
		te2.setNombre("TipoEnfermedad Test 2");
		
		dao.persist(te1);
		dao.persist(te2);
		
		assertEquals(2, dao.findAll().size());
		
		dao.delete(te2);
		
		assertEquals(1, dao.findAll().size());
		
		
		/*
		List<TipoEnfermedad> tiposDeEnfermedades = dao.findAll();
		Assert.assertEquals(te.getNombre(), tiposDeEnfermedades.get(0).getNombre());
		
		*/
	}

}
