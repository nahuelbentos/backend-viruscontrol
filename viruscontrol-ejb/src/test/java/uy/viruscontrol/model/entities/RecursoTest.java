package uy.viruscontrol.model.entities;


import org.junit.Assert;
import org.junit.Test;


import uy.viruscontrol.model.entities.Recurso;


public class RecursoTest {

	@Test
	public void test() {
		
		Recurso r1 = new Recurso("Recurso 1",null);
		
		Assert.assertEquals(r1.getNombre(), "Recurso 1");
	}

}
