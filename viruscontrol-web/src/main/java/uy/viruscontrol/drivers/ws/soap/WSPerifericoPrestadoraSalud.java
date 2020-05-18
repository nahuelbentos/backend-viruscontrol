package uy.viruscontrol.drivers.ws.soap;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.jws.WebService;

import uy.viruscontrol.drivers.PerifericoPrestadoraSaludLocal;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.PrestadoraSalud;

@ApplicationScoped
@WebService (serviceName="PerifericoPrestadoraSalud",
			endpointInterface="uy.viruscontrol.drivers.ws.soap.IWSPerifericoPrestadoraSalud")
public class WSPerifericoPrestadoraSalud implements IWSPerifericoPrestadoraSalud {
	
	@EJB
	private PerifericoPrestadoraSaludLocal prestadora;

	@Override
	public List<Medico> obtenerMedicos(int idPrestadora) {
		return prestadora.obtenerMedicos(idPrestadora);
	}

	@Override
	public boolean estaDisponible(int idPrestadora) {
		return prestadora.estaDisponible(idPrestadora);
	}

	@Override
	public int obtenerMedicoAsignado(int idPrestadora) {
		return prestadora.obtenerMedicoAsignado(idPrestadora);
	}

	@Override
	public PrestadoraSalud obtenerPrestadorDeSalud(int idUsuario) {
		return prestadora.obtenerPrestadorDeSalud(idUsuario);
	}

}
