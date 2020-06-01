package uy.viruscontrol.bussines.serviceagents;

import java.util.List;

import javax.ejb.Remote;

import uy.viruscontrol.model.entities.PrestadoraSalud;

@Remote
public interface ServiceAgentPrestadoraSaludRemote {
	
	// Consultas a Periférico Salud.uy / Rucaf
		
		public List<PrestadoraSalud> obtenerPrestadorasRucaf();

}
