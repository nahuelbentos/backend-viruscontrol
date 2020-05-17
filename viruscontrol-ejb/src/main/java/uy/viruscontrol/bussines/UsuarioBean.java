package uy.viruscontrol.bussines;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.bussines.interfaces.UsuarioBeanLocal;
import uy.viruscontrol.bussines.interfaces.UsuarioBeanRemote;
import uy.viruscontrol.model.dao.interfaces.CiudadanoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.MedicoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.UsuarioDAOLocal;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.Usuario;

@Stateless
@LocalBean
public class UsuarioBean implements UsuarioBeanRemote, UsuarioBeanLocal {

    public UsuarioBean() {}

    @EJB
    private UsuarioDAOLocal usuarioDAO;
    @EJB
    private CiudadanoDAOLocal ciudadanoDAO;
    @EJB
    private MedicoDAOLocal medicoDAO;
    
	@Override
	public boolean autenticarUsuario(String username, String password) {
		Usuario user = usuarioDAO.findByUsername(username);
		if (user == null)
			return false; // El usuario no existe en la base de datos.
		
		return (user.getPassword() == password);
	}


	@Override
	public Usuario getUsuario(String username) {
		return usuarioDAO.findByUsername(username);
	}


	@Override
	public boolean registrarPrimerIngreso(Usuario user, TipoUsuario tipoUser) {
		if (usuarioDAO.existUserByUsername(user.getUsername())) {
			return false;
		} else {
			user.setPrimerIngreso(true);
			switch (tipoUser){
				case CIUDADANO:
					ciudadanoDAO.persist((Ciudadano)user);
					break;
				
				case MEDICO:
					medicoDAO.persist((Medico)user);
					break;
				default:

					// lanzar una excepcion o algo similar porque no puede llegar otro tipo de usuario que no sean estos dos.
					break;
					
			}
			return user.isPrimerIngreso();
		}
			
	}


	@Override
	public void actualizarDatos(Usuario user) {
		Usuario u = usuarioDAO.findByUsername(user.getUsername());
		user.setIdUsuario(u.getIdUsuario());
		try {
			ciudadanoDAO.merge((Ciudadano)user);
		} catch (Exception e) {
			try {
				medicoDAO.merge((Medico)user);
			} catch (Exception e1) {
				e.printStackTrace();
				e1.printStackTrace();
			}
		}
	}
	
	
    
    

}
