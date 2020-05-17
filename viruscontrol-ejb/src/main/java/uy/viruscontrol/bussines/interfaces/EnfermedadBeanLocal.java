package uy.viruscontrol.bussines.interfaces;

import javax.ejb.Local;

@Local
public interface EnfermedadBeanLocal {

	
	boolean altaRecursoRecomendado(String nombreEnfermedad, String nombreRecurso, boolean recursoTrata,
			boolean recursoPreviene);

}
