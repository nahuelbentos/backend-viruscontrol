package uy.viruscontrol.bussines;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import uy.viruscontrol.model.entities.PrestadoraSalud;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.bussines.interfaces.CiudadanoBeanLocal;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentFirebaseLocal;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentPrestadoraSaludLocal;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorRecursoLocal;
import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.CiudadanoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.MedicoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.RecursoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.SintomaDAOLocal;
import uy.viruscontrol.model.dao.interfaces.SuscripcionDAOLocal;
import uy.viruscontrol.model.dao.interfaces.UbicacionDAOLocal;
import uy.viruscontrol.model.dao.interfaces.VisitaMedicoDAOLocal;
import uy.viruscontrol.model.entities.Caso;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Sintoma;
import uy.viruscontrol.model.entities.Suscripcion;
import uy.viruscontrol.model.entities.Ubicacion;
import uy.viruscontrol.model.entities.VisitaMedico;
import uy.viruscontrol.utils.DtExamenCiudadano;
import uy.viruscontrol.utils.DtVisita;
import uy.viruscontrol.utils.firebase.NotificationInfo;
import uy.viruscontrol.utils.firebase.NotificationInfoData;
import uy.viruscontrol.utils.firebase.NotificationPriority;

@Stateless
@Local(CiudadanoBeanLocal.class)
public class CiudadanoBean implements CiudadanoBeanLocal {
	@EJB private CiudadanoDAOLocal daoCiudadano;
	@EJB private MedicoDAOLocal daoMedico;
	@EJB private SintomaDAOLocal daoSintoma;
	@EJB private VisitaMedicoDAOLocal daoVisita;
	@EJB private CasoDAOLocal daoCasoLocal;
	@EJB private RecursoDAOLocal daoRecurso;
	@EJB private SuscripcionDAOLocal daoSuscripcion;
	@EJB private UbicacionDAOLocal daoUbicacion;
	@EJB private ServiceAgentPrestadoraSaludLocal beanPrestador;
	@EJB private ServiceAgentProveedorRecursoLocal saRec;
	@EJB private ServiceAgentFirebaseLocal saFirebase;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
	
	
	public CiudadanoBean() {
		super();
	}

	@Override
	public DtVisita solicitarMedicoADomicilio(int idCiudadano, List<Sintoma> sintomas) {
		try {
			VisitaMedico vm = new VisitaMedico();
			Ciudadano c = daoCiudadano.findById(idCiudadano);
			int idMedico = 0;
			Calendar fecha = Calendar.getInstance();
			
			PrestadoraSalud ps = beanPrestador.obtenerPrestadorDeSalud(c.getIdUsuario());//Integer.parseInt(c.getDocumento()));
			if (beanPrestador.estaDisponible(ps.getId()))
				// si la prestadora del ciudadano está disponible, le pido un médico
				idMedico = beanPrestador.obtenerMedicoAsignado(ps.getId());
			else
				// si la prestadora del ciudadano está saturada, busco una prestadora alternativa y le pido un médico
				idMedico = beanPrestador.obtenerMedicoAsignado(beanPrestador.obtenerPrestadorDeSaludAlternativo(c.getIdUsuario()).getId());
			
			vm.setMedico(daoMedico.findById(idMedico));
			vm.setCiudadano(daoCiudadano.findById(idCiudadano));
			vm.setFechaAsignacion(fecha);
			vm.setVisitaRealizada(false);
			vm.setSintomas(sintomas);
			daoVisita.persist(vm);
			
			DtVisita ret = new DtVisita(vm.getIdVisitaMedico(), vm.getMedico().getIdUsuario(), vm.getMedico().getNombre() + " " + vm.getMedico().getApellido(), sdf.format(fecha.getTime()));
			return ret;
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
			return new DtVisita();
		}
	}
	
	@Override
	public List<DtExamenCiudadano> obtenerExamenesCiudadano(int idCiudadano){
		
		return daoCasoLocal.findAllExamenesByCiudadano(idCiudadano);
	}
	
	@Override
	public void suscribirseARecurso(int idCiudadano,String barrio,int idRecurso) {
		Ciudadano c=daoCiudadano.findById(idCiudadano);
		Recurso r=daoRecurso.findById(idRecurso);
				
		
		
		if (c!=null && r!=null && barrio!=null) {
			Suscripcion s=new Suscripcion();
			s.setBarrio(barrio);
			s.setCiudadano(c);
			s.setRecurso(r);
			s.setNotificado(false);
			daoSuscripcion.persist(s);
		}
		
		
	}
	@Override
	public List<String> obtenerBarrios(){
		List<String> barrios= saRec.getListadoBarrios();
		
		
		
		return barrios;
		
	}
	
	@Override
	public List<String> obtenerCiudades(){
		List<String> ciudades= new ArrayList<String>();
		ciudades.add("Montevideo");

		
		
		return ciudades;
		
	}
	
	@Override
	public void reportarUbicacion(Ubicacion ubicacion, int idCiudadano) {
		ubicacion.setFecha(Calendar.getInstance());
		ubicacion.setCiudadano(daoCiudadano.findById(idCiudadano));
		daoUbicacion.persist(ubicacion);
	}
	
	@Override
	public void actualizarTokenPushNotifications(int idCiudadano, String tokenPushNotifications) {
		Ciudadano c = daoCiudadano.findById(idCiudadano);
		if (c != null) {
			c.setTokenPushNotifications(tokenPushNotifications);
			daoCiudadano.merge(c);
		}
	}
	
	@Override
	public void sendPushNotification(int idCiudadano, String titulo, String texto, NotificationPriority prioridad) {
		Ciudadano c = daoCiudadano.findById(idCiudadano);
		if (c != null) {
			NotificationInfo notificacion = new NotificationInfo(c.getTokenPushNotifications(), prioridad,
											new NotificationInfoData(titulo, texto));
			saFirebase.sendPushNotification(notificacion);
		}
	}

	@Override
	public HashMap<Integer, Ciudadano> obtenerCiudadanosEnfermos() {
		List<Caso> casos = daoCasoLocal.findAllConfirmados();
		HashMap<Integer, Ciudadano> enfermos = new HashMap<Integer, Ciudadano>();
		for (Caso c : casos) {
			if (!enfermos.containsKey(c.getCiudadano().getIdUsuario()))
				enfermos.put(c.getCiudadano().getIdUsuario(), c.getCiudadano());
		}
		return enfermos;
	}

}
