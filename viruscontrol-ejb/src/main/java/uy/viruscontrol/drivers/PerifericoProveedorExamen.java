package uy.viruscontrol.drivers;

import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.EnfermedadDAOLocal;
import uy.viruscontrol.model.dao.interfaces.ExamenDAOLocal;
import uy.viruscontrol.model.dao.interfaces.ProveedorExamenDAOLocal;
import uy.viruscontrol.model.entities.Caso;
import uy.viruscontrol.model.entities.EstadoExamen;
import uy.viruscontrol.model.entities.Examen;
import uy.viruscontrol.model.entities.ProveedorExamen;

@Stateless
@Local(PerifericoProveedorExamenLocal.class)
public class PerifericoProveedorExamen implements PerifericoProveedorExamenLocal {
	
	@EJB private EnfermedadDAOLocal daoEnfermedad;
	@EJB private ExamenDAOLocal daoExamen;
	@EJB private ProveedorExamenDAOLocal daoProvEx;
	@EJB private CasoDAOLocal daoCaso;
	
	public PerifericoProveedorExamen() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ProveedorExamen find(int idEnfermedad) {
		List<ProveedorExamen> pe = daoProvEx.findAllByEnfermedadId(idEnfermedad);
		Random rand = new Random();
		if (pe != null)
			return pe.get(rand.nextInt(pe.size()));
		else
			return null;
	}
	
	@Override
	public List<Examen> listaExamenesParaEnfermedad(int idEnfermedad) {
		return daoExamen.findAllByEnfermedad(idEnfermedad);
	}

	@Override
	public Examen solicitarAltaExamen(int idPaciente, int idExamen, int idMedico) {
		// como se está emulando ser un proveedor de examen, devuelvo una instancia del examen solicitado, pero no la persisto
		return daoExamen.findById(idExamen);
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
