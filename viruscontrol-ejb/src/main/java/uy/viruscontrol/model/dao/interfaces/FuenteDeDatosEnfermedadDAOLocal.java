package uy.viruscontrol.model.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.FuenteDeDatosEnfermedad;

@Local
public interface FuenteDeDatosEnfermedadDAOLocal {

	public void persist(FuenteDeDatosEnfermedad fuenteEnfermedad);

	public void merge(FuenteDeDatosEnfermedad fuenteEnfermedad);

	public List<FuenteDeDatosEnfermedad> findAll();

	public void delete(FuenteDeDatosEnfermedad fuenteEnfermedad);

	public FuenteDeDatosEnfermedad findById(int id);

	public List<FuenteDeDatosEnfermedad> findAllByEnfermedad(Enfermedad e);
}
