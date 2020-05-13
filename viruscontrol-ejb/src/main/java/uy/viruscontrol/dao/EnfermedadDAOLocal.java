package uy.viruscontrol.dao;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.entities.Enfermedad;

@Local
public interface EnfermedadDAOLocal  {
	
	public void persist(Enfermedad entidad);
	
	public void merge(Enfermedad entidad);
	
	public List<Enfermedad> findAll();
	
	public Enfermedad findById(Integer id);

}
