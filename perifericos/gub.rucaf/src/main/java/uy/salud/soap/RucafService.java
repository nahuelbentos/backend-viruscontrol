package uy.salud.soap;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.jws.WebService;

import uy.salud.beans.RucafLocal;
import uy.salud.entities.PrestadoraSalud;

@ApplicationScoped
@WebService (serviceName="RucafService",
			endpointInterface="uy.salud.soap.IRucafService")
public class RucafService implements IRucafService {
	
	@EJB private RucafLocal beanRucaf;
	
	public RucafService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PrestadoraSalud> obtenerPrestadoras() {
		return beanRucaf.obtenerPrestadoras();
	}

	@Override
	public PrestadoraSalud obtenerPrestadoraUsuario(int documento) {
		return beanRucaf.obtenerPrestadoraUsuario(documento);
	}

}
