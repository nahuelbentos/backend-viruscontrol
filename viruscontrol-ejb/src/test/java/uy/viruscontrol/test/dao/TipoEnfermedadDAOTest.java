package uy.viruscontrol.test.dao;

import static org.junit.Assert.*;


//import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;

/*
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
*/
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.junit.Assert;

import uy.viruscontrol.model.dao.interfaces.TipoEnfermedadDAOLocal;
//import uy.viruscontrol.model.dao.interfaces.TipoEnfermedadDAORemote;
//import uy.viruscontrol.model.entities.TipoEnfermedad;


@RunWith(Arquillian.class)
public class TipoEnfermedadDAOTest{
	/*
	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive jar =  ShrinkWrap.create(JavaArchive.class)
	      .addClasses(TipoEnfermedadDAOLocal.class)
	      .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	    System.out.println(jar.toString(true));
	    return jar;
	}
	*/
	
	@EJB
	TipoEnfermedadDAOLocal dao;
	//@EJB(lookup = "ejb:viruscontrol/viruscontrol-ejb/TipoEnfermedadDAO!uy.viruscontrol.model.dao.interfaces.TipoEnfermedadDAORemote")
	//private TipoEnfermedadDAORemote tipoEnfermedadRemote;
 
	
	@Test
	public void daoTest() {
		
		assertTrue(!dao.findAll().isEmpty());
		/*
		TipoEnfermedad te1 = new TipoEnfermedad();
		te1.setNombre("TipoEnfermedad Test 1");
		
		TipoEnfermedad te2 = new TipoEnfermedad();
		te2.setNombre("TipoEnfermedad Test 2");
		
		dao.persist(te1);
		dao.persist(te2);
		
		assertEquals(4, dao.findAll().size());
		
		dao.delete(te2);
		
		assertEquals(3, dao.findAll().size());
		
		
		
		List<TipoEnfermedad> tiposDeEnfermedades = dao.findAll();
		Assert.assertEquals(te1.getNombre(), tiposDeEnfermedades.get(0).getNombre());
		*/
		
	}

}
