package com.examen.beans;

import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.examen.persistence.CasoDAOLocal;
import com.examen.persistence.EnfermedadDAOLocal;
import com.examen.persistence.ExamenDAOLocal;
import com.examen.persistence.ProveedorExamenDAOLocal;
import com.examen.entities.Caso;
import com.examen.entities.Enfermedad;
import com.examen.entities.EstadoExamen;
import com.examen.entities.Examen;
import com.examen.entities.ProveedorExamen;

@Stateless
@Local(PerifericoProveedorExamenLocal.class)
public class PerifericoProveedorExamen implements PerifericoProveedorExamenLocal {
	
	@EJB private EnfermedadDAOLocal daoEnfermedad;
	@EJB private ExamenDAOLocal daoExamen;
	@EJB private ProveedorExamenDAOLocal daoProvEx;
	@EJB private CasoDAOLocal daoCaso;
	
	public PerifericoProveedorExamen() {
		super();
	}

	@Override
	public ProveedorExamen find(int idEnfermedad) {
		List<ProveedorExamen> pe = daoProvEx.findAllByEnfermedadId(idEnfermedad);
		Random rand = new Random();
		if ((pe != null) && pe.size() > 0)
			return pe.get(rand.nextInt(pe.size()));
		else
			return new ProveedorExamen();
	}
	
	@Override
	public List<ProveedorExamen> findAllByEnfermedad(int idEnfermedad) {
		return daoProvEx.findAllByEnfermedadId(idEnfermedad);
	}
	
	@Override
	public List<Examen> listaExamenesParaEnfermedad(int idEnfermedad) {
		return daoExamen.findAllByEnfermedad(idEnfermedad);
	}

	@Override
	public Examen solicitarAltaExamen(int idPaciente, int idExamen, int idMedico) {
		// como se está emulando ser un proveedor de examen, devuelvo una instancia del examen solicitado, pero no la persisto
		Examen ex = daoExamen.findById(idExamen);
		if (ex != null)
			return ex;
		else
			return new Examen(new Enfermedad());
	}

	@Override
	public EstadoExamen resultadoExamen(int idCaso) {
		// emulo estar dando el resultado de un examen desde un prestador. En nuestra estructura, para devolver esa info devuelvo el estado del caso
		Random rand = new Random();
		Caso caso = daoCaso.findById(idCaso);
		if (caso != null) {
			switch (caso.getTipoCaso()) {
				case SOSPECHOSO:
					return EstadoExamen.values()[rand.nextInt(EstadoExamen.values().length)];
				case DESCARTADO:
					return EstadoExamen.NEGATIVO;
				default: // los casos CONFIRMADO y RECUPERADO corresponden a casos cuyo examen ha dado positivo, por ahora incluyo también en el default EXPOSICION, hay que ver si se necesita moverlo
					return EstadoExamen.POSITIVO;
			}
		} else
			return EstadoExamen.PENDIENTE;
	}

}
