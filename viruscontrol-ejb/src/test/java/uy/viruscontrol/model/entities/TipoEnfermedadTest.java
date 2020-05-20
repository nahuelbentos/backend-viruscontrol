package uy.viruscontrol.model.entities;

import org.junit.Assert;
import org.junit.Test;


import uy.viruscontrol.model.entities.TipoEnfermedad;


public class TipoEnfermedadTest {

	@Test
	public void test() {
		
		TipoEnfermedad te1 = new TipoEnfermedad();
		
		te1.setNombre("TipoEnfermedad Test 1");
		
		Assert.assertEquals(te1.getNombre(), "TipoEnfermedad Test 1");
		
	}

}
