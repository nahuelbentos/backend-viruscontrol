package uy.viruscontrol.bussines.serviceagents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.ObjectMapper;

import uy.viruscontrol.model.entities.EstadoExamen;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.utils.CaracteresDeEscapePersonalizados;
import uy.viruscontrol.utils.DtExamen;

@Stateless
@Local(ServiceAgentProveedorExamenLocal.class)
public class ServiceAgentProveedorExamen implements ServiceAgentProveedorExamenLocal {
	private static final String urlProvExRest = "http://localhost:8080/proveedores-examenes/rest/perifprovex/";
	private static ObjectMapper mapper;
	
	public ServiceAgentProveedorExamen() {
		super();
		mapper = new ObjectMapper();
		mapper.getFactory().setCharacterEscapes(new CaracteresDeEscapePersonalizados());
	}

	@Override
	public ProveedorExamen obtenerProveedor(int idEnfermedad) throws ClientProtocolException, IOException {
		// TESTEADO
		// Método @GET - Uso HttpGet - Devuelve JSON
		HttpClient client = HttpClients.createDefault();
		HttpGet getRequest = new HttpGet(urlProvExRest + "proveedor/" + idEnfermedad);
		HttpResponse res = client.execute(getRequest);
		
		return mapper.readValue(res.getEntity().getContent(), ProveedorExamen.class);
	}

	@Override
	public List<ProveedorExamen> obtenerProveedores(int idEnfermedad) throws ClientProtocolException, IOException {
		// Método @GET - Uso HttpGet - Devuelve JSON
		HttpClient client = HttpClients.createDefault();
		HttpGet getRequest = new HttpGet(urlProvExRest + "proveedores/" + idEnfermedad);
		HttpResponse res = client.execute(getRequest);
		
		return mapper.readValue(res.getEntity().getContent(), mapper.getTypeFactory().constructCollectionType(List.class, ProveedorExamen.class));
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
	public DtExamen altaDeExamen(int idPaciente, int idExamen, int idMedico) throws ClientProtocolException, IOException {
		// Método @POST - Uso HttpPost - Devuelve JSON
		HttpClient client = HttpClients.createDefault();
		HttpPost postRequest = new HttpPost(urlProvExRest + "examen");
		
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("idPaciente", Integer.toString(idPaciente)));
		formParams.add(new BasicNameValuePair("idExamen", Integer.toString(idExamen)));
		formParams.add(new BasicNameValuePair("idMedico", Integer.toString(idMedico)));
		
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
		
		postRequest.setEntity(entity);
	    HttpResponse res = client.execute(postRequest);
	    
		return mapper.readValue(res.getEntity().getContent(), DtExamen.class);
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
