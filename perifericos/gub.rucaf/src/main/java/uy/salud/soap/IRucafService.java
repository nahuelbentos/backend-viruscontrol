package uy.salud.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import uy.salud.entities.PrestadoraSalud;

@WebService
public interface IRucafService {
	@WebMethod
	public List<PrestadoraSalud> obtenerPrestadoras();
	
	@WebMethod
	public PrestadoraSalud obtenerPrestadoraUsuario(@WebParam(name="documento") int documento);
}
