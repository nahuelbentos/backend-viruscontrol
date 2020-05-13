package uy.viruscontrol.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericDAO<T extends Serializable, E>{
	
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager manager;
	
	public void persist(T entidad) {
		manager.persist(entidad);
	}
	
	public void merge(T entidad) {
		manager.merge(entidad);
	}
	
	public abstract List<T> findAll();
	
	public abstract T findById(E id);
}
