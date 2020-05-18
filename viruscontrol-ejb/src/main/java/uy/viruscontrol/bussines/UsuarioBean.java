package uy.viruscontrol.bussines;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;

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
		
		try {
			return (user.getPassword() == hashPassword(password));
		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
			System.out.println("ERROR ["+UsuarioBean.class.getName()+"]: No se pudo obtener el hash MD5 para la password.");
			return false;
		}
	}
	
	private String hashPassword(String clave) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(clave.getBytes());
		byte[] digest = md.digest();
		String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		
		return myHash;
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
			
			// Si la password es null, el usuario es de redes sociales, por lo que no la guardo. de lo contrario, guardo el hash md5 de la misma
			if (user.getPassword() != null) {
				try {
					user.setPassword(hashPassword(user.getPassword()));
				} catch (NoSuchAlgorithmException e) {
//					e.printStackTrace();
					System.out.println("ERROR ["+UsuarioBean.class.getName()+"]: No se pudo obtener el hash MD5 para la password.");
				}
			}
			
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
