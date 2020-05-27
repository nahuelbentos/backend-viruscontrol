package com.prestador.services.soap;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.jws.WebService;

import com.prestador.beans.PerifericoPrestadoraSaludLocal;
import com.prestador.entities.Medico;
import com.prestador.entities.PrestadoraSalud;

@ApplicationScoped
@WebService (serviceName="PerifericoPrestadoraSalud",
			endpointInterface="com.prestador.services.soap.IWSPerifericoPrestadoraSalud")
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
	public PrestadoraSalud obtenerPrestadorDeSaludAlternativo(int idUsuario) {
		return prestadora.obtenerPrestadorDeSaludAlternativo(idUsuario);
	}

}
