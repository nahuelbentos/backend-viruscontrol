package com.prestador.persistence;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.prestador.entities.Usuario;


@Stateless
@Local(UsuarioDAOLocal.class)
public class UsuarioDAO implements UsuarioDAOLocal {

	@PersistenceContext(unitName = "com.prestadorsaludPersistenceUnit")
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
