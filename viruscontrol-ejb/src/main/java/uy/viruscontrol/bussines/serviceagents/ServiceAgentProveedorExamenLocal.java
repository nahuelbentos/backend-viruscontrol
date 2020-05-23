package uy.viruscontrol.bussines.serviceagents;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;

import org.apache.http.client.ClientProtocolException;

import uy.viruscontrol.model.entities.EstadoExamen;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.utils.DtExamen;

@Local
public interface ServiceAgentProveedorExamenLocal {
	public ProveedorExamen obtenerProveedor(int idEnfermedad) throws ClientProtocolException, IOException;
	public List<ProveedorExamen> obtenerProveedores(int idEnfermedad) throws ClientProtocolException, IOException;
	public List<DtExamen> obtenerExamenesParaUnaEnfermedad(int idEnfermedad) throws ClientProtocolException, IOException;
	public DtExamen altaDeExamen(int idPaciente, int idExamen, int idMedico) throws ClientProtocolException, IOException;
	public EstadoExamen obtenerResultadoExamen(int idCaso) throws ClientProtocolException, IOException;
}
