package uy.viruscontrol.model.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uy.viruscontrol.model.dao.impl.UsuarioDAO;



public class UsuarioDAOTest {

	
	@Mock
	private UsuarioDAO dao;
	
	//@Mock
	//Usuario usuarioAdmin = new Administrador();
	
	@Before

    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);

    }
	
	@Test
	public void testFindByUsername() {
		dao.findByUsername("maxifarkuso");
		Mockito.verify(dao).findByUsername("maxifarkuso");
	}

	@Test
	public void testExistUserByUsername() {
		dao.existUserByUsername("maxifarkuso");
		Mockito.verify(dao).existUserByUsername("maxifarkuso");
	}

}
