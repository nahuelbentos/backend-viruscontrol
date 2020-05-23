package uy.viruscontrol.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.viruscontrol.model.dao.interfaces.ExamenDAOLocal;
import uy.viruscontrol.model.dao.interfaces.ProveedorExamenDAOLocal;
import uy.viruscontrol.model.entities.Examen;
import uy.viruscontrol.model.entities.ProveedorExamen;



@Stateless
@LocalBean
public class ProveedorExamenDAO implements ProveedorExamenDAOLocal {
	
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
	@EJB private ExamenDAOLocal daoExamen;
	
	public ProveedorExamenDAO() {
		
	}
	@Override
	public void persist(ProveedorExamen proveedorExamen) {
	
		em.persist(proveedorExamen);
		
	}
	
	@Override
	public void merge(ProveedorExamen proveedorExamen) {
		
		em.merge(proveedorExamen);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProveedorExamen> findAll() {

		Query q = em.createQuery("SELECT p FROM ProveedorExamen p");
		return q.getResultList();
	}
	
	@Override
	public ProveedorExamen findById(Integer id) {
		
		return em.find(ProveedorExamen.class, id);
	}

	@Override
	public void delete(ProveedorExamen proveedorExamen) {
		
		em.remove(em.contains(proveedorExamen) ? proveedorExamen : em.merge(proveedorExamen));
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProveedorExamen> findAllByEnfermedadId(int idEnfermedad) {
		List<Examen> examenes = daoExamen.findAllByEnfermedad(idEnfermedad);
//		for (Examen e : examenes)
//			System.out.println("SE HA ENCONTRADO EL EXAMEN CON ID: "+e.getId());
//		System.out.println("Generated IN clause: ("+generateInClauseFromList(examenes, 0)+")");
//		String query = "SELECT p.id, p.barrio, p.direccion, p.nombre, p.rangohorario "+ 
//					"FROM proveedor p INNER JOIN proveedorexamen_examen pee ON pee.id_proveedor = p.id "+
//					"WHERE p.proveedor_tipo = 'EXAMEN' "+
//					"AND pee.id_examen in ("+generateInClauseFromList(examenes, 0)+");";
//		System.out.println(query);
		
		List<Object[]> lista = em.createNativeQuery(
					"SELECT p.id, p.barrio, p.direccion, p.nombre, p.rangohorario "+ 
					"FROM proveedor p INNER JOIN proveedorexamen_examen pee ON pee.id_proveedor = p.id "+
					"WHERE p.proveedor_tipo = 'EXAMEN' "+
					"AND pee.id_examen in ("+generateInClauseFromList(examenes, 0)+");").getResultList();
		List<ProveedorExamen> ret = new ArrayList<ProveedorExamen>();
		for (Object[] pe : lista) {
			ret.add(new ProveedorExamen(Integer.parseInt(pe[0].toString()),
										pe[1].toString(),
										pe[2].toString(),
										pe[3].toString(),
										pe[4].toString()));
		}
		return ret;
		
		// La documentación de HQL dice que la cláusula in funciona de esta manera, pero se probó y revienta
//		return em.createQuery("FROM ProveedorExamen WHERE examenes in (:examenes)")
//				.setParameter(":examenes", examenes)
//				.getResultList();
	}
	
	private String generateInClauseFromList(List<Examen> examenes, int index) {
		if (index == examenes.size()-1) {
			return Integer.toString(examenes.get(index).getId());
		} else {
			return Integer.toString(examenes.get(index).getId()) + ", " + generateInClauseFromList(examenes, index+1);
		}
	}


}
