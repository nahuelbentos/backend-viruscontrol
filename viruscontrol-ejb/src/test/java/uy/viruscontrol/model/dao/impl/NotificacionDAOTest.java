package uy.viruscontrol.model.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uy.viruscontrol.model.dao.impl.NotificacionDAO;
import uy.viruscontrol.model.entities.Notificacion;


public class NotificacionDAOTest {

	@Mock
	private NotificacionDAO dao;
	
	@Before

    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);

    }
	@Test
	public void testPersist() {
		Notificacion obj = new Notificacion();
		dao.persist(obj);
		Mockito.verify(dao).persist(obj);
	}

	@Test
	public void testMerge() {
		Notificacion obj = new Notificacion();
		dao.merge(obj);
		Mockito.verify(dao).merge(obj);
	}

	@Test
	public void testFindAll() {
		dao.findAll();
		Mockito.verify(dao).findAll();
	}

	@Test
	public void testFindById() {
		dao.findById(100);
		Mockito.verify(dao).findById(100);
	}

	@Test
	public void testDelete() {
		Notificacion obj = new Notificacion();
		dao.delete(obj);
		Mockito.verify(dao).delete(obj);
	}

}
