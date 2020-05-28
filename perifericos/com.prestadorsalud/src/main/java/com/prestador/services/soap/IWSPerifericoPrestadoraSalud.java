package com.prestador.services.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.prestador.entities.Medico;
import com.prestador.entities.PrestadoraSalud;

@WebService
public interface IWSPerifericoPrestadoraSalud {
	@WebMethod
	public List<Medico> obtenerMedicos(@WebParam(name="idPrestadora")
									  int idPrestadora);
	
	@WebMethod
	public boolean estaDisponible(@WebParam(name="idPrestadora")
								 int idPrestadora);
	
	@WebMethod
	public int obtenerMedicoAsignado(@WebParam(name="idPrestadora")
									int idPrestadora);
	
	@WebMethod
	public PrestadoraSalud obtenerPrestadorDeSaludAlternativo(@WebParam(name="idUsuario")
												  			 int idUsuario);
}
