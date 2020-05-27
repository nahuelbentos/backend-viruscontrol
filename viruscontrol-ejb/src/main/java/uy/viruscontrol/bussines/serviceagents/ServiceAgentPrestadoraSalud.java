package uy.viruscontrol.bussines.serviceagents;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import uy.salud.services.soap.rucaf.IRucafService;
import uy.salud.services.soap.rucaf.RucafService;
import com.prestadorsalud.services.soap.IWSPerifericoPrestadoraSalud;
import com.prestadorsalud.services.soap.PerifericoPrestadoraSalud;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.PrestadoraSalud;

@Stateless
@Local(ServiceAgentPrestadoraSaludLocal.class)
public class ServiceAgentPrestadoraSalud implements ServiceAgentPrestadoraSaludLocal {
	private PerifericoPrestadoraSalud servicioPS;
	private IWSPerifericoPrestadoraSalud clientePS;
	private RucafService servicioRucaf;
	private IRucafService rucaf;
	
	public ServiceAgentPrestadoraSalud() {
		super();
		this.servicioPS = new PerifericoPrestadoraSalud();
		this.clientePS = servicioPS.getWSPerifericoPrestadoraSaludPort();
		this.servicioRucaf = new RucafService();
		this.rucaf = servicioRucaf.getRucafServicePort();
	}
	
	// Consultas a Periférico Prestadoras de Salud ****************************************************************
	@Override
	public List<Medico> obtenerMedicos(int idPrestadora) {
		List<com.prestadorsalud.services.soap.Medico> res = clientePS.obtenerMedicos(idPrestadora);
		List<Medico> lista = new ArrayList<Medico>();
		for (com.prestadorsalud.services.soap.Medico it : res) {
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
		return clientePS.estaDisponible(idPrestadora);
	}

	@Override
	public int obtenerMedicoAsignado(int idPrestadora) {
		return clientePS.obtenerMedicoAsignado(idPrestadora);
	}

	@Override
	public PrestadoraSalud obtenerPrestadorDeSaludAlternativo(int idUsuario) {
		com.prestadorsalud.services.soap.PrestadoraSalud res = clientePS.obtenerPrestadorDeSaludAlternativo(idUsuario);
		if (res != null)
			return new PrestadoraSalud(res.getId(), res.getNombre());
		else
			return null;
	}
	
	// Consultas a Periférico Salud.uy / Rucaf ********************************************************************
	@Override
	public PrestadoraSalud obtenerPrestadorDeSalud(int documento) {
		uy.salud.services.soap.rucaf.PrestadoraSalud res = rucaf.obtenerPrestadoraUsuario(documento);
		return new PrestadoraSalud(res.getId(), res.getNombre());
	}

}
