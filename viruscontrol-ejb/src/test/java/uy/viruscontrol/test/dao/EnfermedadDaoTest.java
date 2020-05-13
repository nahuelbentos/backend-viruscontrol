package uy.viruscontrol.test.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;


import org.junit.Test;

import org.junit.Assert;

import uy.viruscontrol.model.dao.interfaces.EnfermedadDAOLocal;
import uy.viruscontrol.model.entities.Enfermedad;


@ApplicationScoped
public class EnfermedadDaoTest {

	@EJB
	private EnfermedadDAOLocal dao;
	
	@Test
	public void persistir() {
		
		Enfermedad e = new Enfermedad();
		e.setNombre("Prueba");
		e.setAprobada(false);
		e.setNombreAgente("Agente Test");
		
		dao.persist(e);
		
		List<Enfermedad> enfermedades = dao.findAll();
		Assert.assertEquals(e.getNombre(), enfermedades.get(0).getNombre());
	}
	
	/*
	 * 
	private String nombre;
	private boolean aprobada;
	private String nombreAgente;
	private TipoEnfermedad tipoEnfermedad;
	private List<Sintoma> sintomas;
	private List<Recurso> recursos;
	 * 
	 * */
	
}
