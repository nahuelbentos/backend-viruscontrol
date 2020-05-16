package uy.viruscontrol.test.entities;



import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.Sintoma;
import uy.viruscontrol.model.entities.TipoEnfermedad;




public class EnfermedadTest {

	@Test
	public void test() {
		
		Enfermedad e1 = new Enfermedad();
		
		List<Recurso> recursos = new ArrayList<>();
		Recurso r1 = new Recurso(1,"recurso 1",null);
		Recurso r2 = new Recurso(2,"recurso 2",null);
		recursos.add(r1);
		recursos.add(r2);
		
		List<Sintoma> sintomas = new ArrayList<>();
		Sintoma s1 = new Sintoma(1,"sintoma 1");
		Sintoma s2 = new Sintoma(2,"sintoma 2");
		sintomas.add(s1);
		sintomas.add(s2);
		
		TipoEnfermedad te = new TipoEnfermedad(1,"Tipo enfermedad test");
		
		
		e1.setNombre("Enfermedad Test 1");
		e1.setAprobada(true);
		e1.setNombreAgente("Agente Test");
		e1.setRecursos(recursos);
		e1.setTipoEnfermedad(te);
		e1.setSintomas(sintomas);
		
		Assert.assertEquals(e1.getNombre(), "Enfermedad Test 1");
		Assert.assertEquals(e1.isAprobada(), true);
		Assert.assertEquals(e1.getNombreAgente(), "Agente Test");
		Assert.assertEquals(e1.getRecursos().get(0).getNombre(), recursos.get(0).getNombre());
		Assert.assertEquals(e1.getSintomas().get(0).getNombre(), sintomas.get(0).getNombre());
		Assert.assertEquals(e1.getTipoEnfermedad().getNombre(), te.getNombre());
		
		
	}


}
