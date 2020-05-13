package uy.viruscontrol.test.dao;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import org.junit.Test;

import uy.viruscontrol.dao.EnfermedadDAOLocal;
import uy.viruscontrol.entities.Enfermedad;

@ApplicationScoped
public class EnfermedadDaoTest {

	@EJB
	private EnfermedadDAOLocal dao;
	
	@Test
	public void persistir() {
		Enfermedad e = new Enfermedad();
		e.setNombre("prueba");
		
		dao.persist(e);
	}
	
}
