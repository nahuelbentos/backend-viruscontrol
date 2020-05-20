package uy.viruscontrol.bussines.serviceagents;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;

import uy.viruscontrol.model.entities.EstadoExamen;
import uy.viruscontrol.model.entities.Examen;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.utils.CaracteresDeEscapePersonalizados;
import uy.viruscontrol.utils.DtExamen;

@Stateless
@Local(ServiceAgentProveedorExamenLocal.class)
public class ServiceAgentProveedorExamen implements ServiceAgentProveedorExamenLocal {
	private static final String urlProvExRest = "http://localhost:8080/viruscontrol-web/rest/perifprovex/";
	private static ObjectMapper mapper;
	
	public ServiceAgentProveedorExamen() {
		super();
		mapper = new ObjectMapper();
		mapper.getFactory().setCharacterEscapes(new CaracteresDeEscapePersonalizados());
	}

	@Override
	public ProveedorExamen obtenerProveedor(int idEnfermedad) throws ClientProtocolException, IOException {
		// Método @GET - Uso HttpGet - Devuelve JSON
		HttpClient client = HttpClients.createDefault();
		HttpGet getRequest = new HttpGet(urlProvExRest + "proveedor/" + idEnfermedad);
		HttpResponse res = client.execute(getRequest);
		
		return mapper.readValue(res.getEntity().getContent(), ProveedorExamen.class);
	}

	@Override
	public List<DtExamen> obtenerExamenesParaUnaEnfermedad(int idEnfermedad) throws ClientProtocolException, IOException {
		// TESTEADO
		// Método @GET - Uso HttpGet - Devuelve JSON
		HttpClient client = HttpClients.createDefault();
		HttpGet getRequest = new HttpGet(urlProvExRest + "examenes/" + idEnfermedad);
		HttpResponse res = client.execute(getRequest);
		
		return mapper.readValue(res.getEntity().getContent(), mapper.getTypeFactory().constructCollectionType(List.class, DtExamen.class));
	}

	@Override
	public Examen altaDeExamen(int idPaciente, int idExamen, int idMedico) {
		// Método @POST
		return null;
	}

	@Override
	public EstadoExamen obtenerResultadoExamen(int idCaso) throws ClientProtocolException, IOException {
		// TESTEADO
		// Método @GET - Uso HttpGet - Devuelve JSON
		HttpClient client = HttpClients.createDefault();
		HttpGet getRequest = new HttpGet(urlProvExRest + "resultados/" + idCaso);
		HttpResponse res = client.execute(getRequest);
		
		return mapper.readValue(res.getEntity().getContent(), EstadoExamen.class);
	}

}
