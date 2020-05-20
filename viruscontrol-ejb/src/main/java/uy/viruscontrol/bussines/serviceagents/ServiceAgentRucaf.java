package uy.viruscontrol.bussines.serviceagents;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import uy.viruscontrol.externalservices.soap.IWSPerifericoPrestadoraSalud;
import uy.viruscontrol.externalservices.soap.PerifericoPrestadoraSalud;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.PrestadoraSalud;

@Stateless
@Local(ServiceAgentRucafLocal.class)
public class ServiceAgentRucaf implements ServiceAgentRucafLocal {
	private PerifericoPrestadoraSalud servicio;
	private IWSPerifericoPrestadoraSalud cliente;
	
	public ServiceAgentRucaf() {
		super();
		this.servicio = new PerifericoPrestadoraSalud();
		this.cliente = servicio.getWSPerifericoPrestadoraSaludPort();
	}

	@Override
	public List<Medico> obtenerMedicos(int idPrestadora) {
		List<uy.viruscontrol.externalservices.soap.Medico> res = cliente.obtenerMedicos(idPrestadora);
		List<Medico> lista = new ArrayList<Medico>();
		for (uy.viruscontrol.externalservices.soap.Medico it : res) {
			PrestadoraSalud ps = new PrestadoraSalud(it.getPrestadoraSalud().getId(), it.getPrestadoraSalud().getNombre());
			Medico m = new Medico(it.getIdUsuario()
					, it.getNombre()
					, it.getApellido()
					, it.getDireccion()
					, it.getFechaNacimiento().toGregorianCalendar()
					, it.getNacionalidad()
					, it.getCorreo()
					, it.getUsername()
					, it.getPassword()
					, it.isPrimerIngreso()
					, ps);
			lista.add(m);
		}
		return lista;
	}

	@Override
	public boolean estaDisponible(int idPrestadora) {
		return cliente.estaDisponible(idPrestadora);
	}

	@Override
	public int obtenerMedicoAsignado(int idPrestadora) {
		return cliente.obtenerMedicoAsignado(idPrestadora);
	}

	@Override
	public PrestadoraSalud obtenerPrestadorDeSalud(int idUsuario) {
		/* 
		 * CUIDADO: si el driver no encuentra al usuario solicitado, no encontrará una prestadora asociada,
		 * por lo que este método puede devolver null. Chequear el resultado en el código que llame a esta
		 * operación.
		 */
		uy.viruscontrol.externalservices.soap.PrestadoraSalud res = cliente.obtenerPrestadorDeSalud(idUsuario);
		if (res != null)
			return new PrestadoraSalud(res.getId(), res.getNombre());
		else
			return null;
	}

}
