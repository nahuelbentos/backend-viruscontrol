package uy.viruscontrol.model.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.CiudadanoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.VisitaMedicoDAOLocal;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.VisitaMedico;

@Stateless
@LocalBean
public class VisitaMedicoDAO implements VisitaMedicoDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
	public VisitaMedicoDAO() {
		
	}
	
	@Override
	public void persist(VisitaMedico visitaMedico) {
	
//		em.persist(visitaMedico);
		// [WORKAROUND] se utiliza native query porque revienta mapeando los tipos
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		em.createNativeQuery(
				"INSERT INTO visita_medico (ciudadano, fechaasignacion, medico, visita_realizada) " + 
				"VALUES (" + 
						visitaMedico.getCiudadano().getIdUsuario() + ", " +
						"'"+sdf.format(visitaMedico.getFechaAsignacion().getTime()) + "', " +
						visitaMedico.getMedico().getIdUsuario() + "," + 
						visitaMedico.isVisitaRealizada() + " );").executeUpdate();
		
	}
	
	@Override
	public void merge(VisitaMedico visitaMedico) {
		
		em.merge(visitaMedico);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VisitaMedico> findAll() {

		Query q = em.createQuery("SELECT v FROM VisitaMedico v");
		return q.getResultList();
	}

	
	@Override
	public VisitaMedico findById(Integer id) {
		
		return em.find(VisitaMedico.class, id);
	}
	
	@Override
	public void delete(VisitaMedico visitaMedico) {
		
		em.remove(em.contains(visitaMedico) ? visitaMedico : em.merge(visitaMedico));
		
	}

	
	
	/*********************************************************************************
	 ***    HAY QUE MODIFICAR LA ENTIDAD VISITA MEDICO PARA QUE PUEDA MANEJAR      ***
	 ***         OBJETOS DE MANERA DE PODER SACAR LAS INYECCIONES DE LOS           ***
	 ***                     DAOS DEFINIDOS A CONTINUACION.                        ***
	 *********************************************************************************/
	@EJB CiudadanoDAOLocal ciudadanoDAO;
	
	@Override
	public List<VisitaMedico> findByMedico(Medico m) { 
		// [WORKAROUND] sigo manejando native query porque hibernate no 
		// reconoce al medico como un int, sino como un bytearr.
		
		@SuppressWarnings("unchecked")
		List<Object[]> rows = em.createNativeQuery(
				"SELECT vm.ciudadano,vm.fechaasignacion,vm.medico,vm.visita_realizada "
				+ "FROM visita_medico vm WHERE vm.medico = " + m.getIdUsuario()
				+ "ORDER BY vm.medico").getResultList();
		
		List<VisitaMedico> visitas = new ArrayList<VisitaMedico>();
		for (Object[] row : rows) {
			VisitaMedico vm = new VisitaMedico();
			vm.setCiudadano(ciudadanoDAO.findById((int)row[0]));
			vm.setMedico(m);
			vm.setVisitaRealizada((boolean)row[3]);
			
			Timestamp tm = (Timestamp)row[1];
			Calendar cal=GregorianCalendar.getInstance();
			cal.setTime(tm);
			vm.setFechaAsignacion(cal);
			
			visitas.add(vm);
		}
		return visitas;
	}
	
	
	
	
}
