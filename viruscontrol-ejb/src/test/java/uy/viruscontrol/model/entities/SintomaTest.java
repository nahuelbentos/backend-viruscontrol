package uy.viruscontrol.model.entities;



import org.junit.Assert;
import org.junit.Test;


import uy.viruscontrol.model.entities.Sintoma;


public class SintomaTest {

	@Test
	public void test() {
		
		Sintoma s1 = new Sintoma();
		s1.setNombre("Sintoma Test 1");
		
		Assert.assertEquals(s1.getNombre(), "Sintoma Test 1");
		
	}

}
