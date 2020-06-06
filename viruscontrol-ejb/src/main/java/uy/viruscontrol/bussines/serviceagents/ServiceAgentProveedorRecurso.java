package uy.viruscontrol.bussines.serviceagents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recurso.model.entities.DummyProveedor;
import com.recurso.model.entities.DummyRecursoDisponible;

import uy.viruscontrol.model.dao.interfaces.ProveedorRecursoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.RecursoDAOLocal;
import uy.viruscontrol.model.entities.ProveedorRecursos;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.TipoRecurso;
import uy.viruscontrol.utils.CaracteresDeEscapePersonalizados;
import uy.viruscontrol.utils.DtRecursosProveedor;

@Stateless
@LocalBean
public class ServiceAgentProveedorRecurso implements ServiceAgentProveedorRecursoLocal,ServiceAgentProveedorRecursoRemote {

	private static final String urlProvRecRest = "http://localhost:8080/proveedores-recursos/rest/proveedor/";
	private static ObjectMapper mapper;
	
	@EJB private ProveedorRecursoDAOLocal daoProvRec;
	@EJB private RecursoDAOLocal daoRecurso;
	
    public ServiceAgentProveedorRecurso() {
    	mapper = new ObjectMapper();
    	mapper.getFactory().setCharacterEscapes(new CaracteresDeEscapePersonalizados());
	}
    

	@Override
	public List<ProveedorRecursos> getProveedoresPeriferico() {
		
		try {
			HttpClient client = HttpClients.createDefault();
			HttpGet getRequest = new HttpGet(urlProvRecRest + "all");
		
			HttpResponse res = client.execute(getRequest);
			List<DummyProveedor> dummiesprov = mapper.readValue(res.getEntity().getContent(), mapper.getTypeFactory().constructCollectionType(List.class, DummyProveedor.class));
			
			
			List<ProveedorRecursos> pprr = new ArrayList<ProveedorRecursos>();
			for (DummyProveedor dummyProveedor : dummiesprov) {
				ProveedorRecursos pr = new ProveedorRecursos();
				pr.setBarrio(dummyProveedor.getBarrio());
				pr.setDireccion(dummyProveedor.getDireccion());
				pr.setRangoHorario(dummyProveedor.getHorarioAtencion());
				pr.setNombre(dummyProveedor.getNombre());
				pr.setCodigoPeriferico(dummyProveedor.getCodigo());
				pprr.add(pr);
			}
			
			return pprr;
			
		} catch (IOException e) {
			//e.printStackTrace();
			this.log("ERROR: "+e.getMessage()+". Para ver mas información, habilitar la traza y replicar el error.");
			return null;
		}
	}

	@Override
	public List<Recurso> getRecursosProvPeriferico(String codigoPeriferico) {
		try {
			HttpClient client = HttpClients.createDefault();
			HttpGet getRequest = new HttpGet(urlProvRecRest + codigoPeriferico);
		
			HttpResponse res = client.execute(getRequest);
			List<DummyRecursoDisponible> dummiesrecprov = mapper.readValue(res.getEntity().getContent(), mapper.getTypeFactory().constructCollectionType(List.class, DummyRecursoDisponible.class));
			
			
			List<Recurso> recursos = new ArrayList<Recurso>();
			for (DummyRecursoDisponible drd : dummiesrecprov ) {
				Recurso r = new Recurso();
				r.setNombre(drd.getRecurso().getMarca());
				
				TipoRecurso tr = new TipoRecurso();
				tr.setNombre(drd.getRecurso().getTipoRecurso().getNombre());
				tr.setDescripcion(drd.getRecurso().getTipoRecurso().getCodigo());
				r.setTipoRecurso(tr);
				
				recursos.add(r);
			}
			
			return recursos;
			
		} catch (IOException e) {
			//e.printStackTrace();
			this.log("ERROR: "+e.getMessage()+". Para ver mas información, habilitar la traza y replicar el error.");
			return null;
		}
	}

	@Override
	public boolean comprarRecursoDisponible(String codigoProveedor, String codigoRecurso) {
		return false;
	}
    
    @Override
    public List<DtRecursosProveedor> getRecursosDisponiblesPorCiudadBarrio(String ciudad, String barrio) {
    	try {
			HttpClient client = HttpClients.createDefault();
			HttpGet getRequest = new HttpGet(urlProvRecRest + "ciudad/" + ciudad + "/barrio/" + barrio);
			
			HttpResponse res = client.execute(getRequest);
			List<DummyProveedor> dummiesProv = mapper.readValue(res.getEntity().getContent(), mapper.getTypeFactory().constructCollectionType(List.class, DummyProveedor.class));
			
			List<DtRecursosProveedor> disponibles = new ArrayList<DtRecursosProveedor>();
			ProveedorRecursos provRec = null;
			
			for (DummyProveedor dp : dummiesProv) {
				provRec = daoProvRec.findByExternalId(dp.getCodigo());
				
				// si el proveedor existe en mi sistema
				if (provRec != null) {
					DtRecursosProveedor dtRP = new DtRecursosProveedor();
					dtRP.setProveedor(provRec);
					
					for (DummyRecursoDisponible it : dp.getRecursosDisponibles()) {
						// obtengo el recurso
						Recurso r = new Recurso();
						r.setNombre(it.getRecurso().getMarca());
						TipoRecurso tr = new TipoRecurso();
						tr.setNombre(it.getRecurso().getTipoRecurso().getNombre());
						tr.setDescripcion(it.getRecurso().getTipoRecurso().getCodigo());
						r.setTipoRecurso(tr);
						
						dtRP.addRecurso(r);
					}
					disponibles.add(dtRP);
				}
			}
			return disponibles;
			
		} catch (IOException e) {
			this.log("ERROR: "+e.getMessage()+". Para ver mas información, habilitar la traza y replicar el error.");
//			e.printStackTrace();
			return new ArrayList<DtRecursosProveedor>();
		}
    }
    
    private void log(String ln) {
    	System.out.println("["+getClass().getCanonicalName()+"] "+ln);
    }
}
