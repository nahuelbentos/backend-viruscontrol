package uy.viruscontrol.model.dao.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import uy.viruscontrol.model.dao.interfaces.UsuarioDAOLocal;
import uy.viruscontrol.model.entities.Usuario;


@Stateless
@LocalBean
public class UsuarioDAO implements UsuarioDAOLocal {

	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
    public UsuarioDAO() {}

	@Override
	public Usuario findByUsername(String username) {
		try {
			Usuario u = (Usuario)em.createQuery("FROM Usuario WHERE username = :username").setParameter("username", username).getSingleResult();
			
			return u;
		}catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public boolean existUserByUsername(String username) {
		try {
			em.createQuery("FROM Usuario WHERE username = :username").setParameter("username", username).getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
    
    

}
