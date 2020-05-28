package uy.viruscontrol.bussines;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.bussines.interfaces.SessionBeanLocal;
import uy.viruscontrol.bussines.interfaces.SessionBeanRemote;
import uy.viruscontrol.bussines.interfaces.UsuarioBeanLocal;
import uy.viruscontrol.model.dao.interfaces.UsuarioDAOLocal;
import uy.viruscontrol.model.entities.Usuario;
import uy.viruscontrol.proxies.ProxyRedesSocialesLocal;
import uy.viruscontrol.utils.UserAuthFE;

@Singleton
@LocalBean
public class SessionBean implements SessionBeanRemote, SessionBeanLocal {

	@EJB
	private UsuarioBeanLocal usuEJB;
	@EJB
	private ProxyRedesSocialesLocal socialMediaClient;
	@EJB
	private UsuarioDAOLocal usuDAO;
	
	private Map<String,Usuario> userConectados = new HashMap<String, Usuario>();
	
	public SessionBean() {}

	@Override
	public AuthResponse iniciarSesion(String username, String password) {
		
		if (usuEJB.autenticarUsuario(username, password)) {
			userConectados.put(username, usuEJB.getUsuario(username));
			return AuthResponse.OK;
		}else
			return AuthResponse.FAILED;
	}

	@Override
	public UserAuthFE iniciarSesionConRedes(Usuario user, TipoUsuario tipoUser) {
		UserAuthFE ret = new UserAuthFE();
		
		if (!socialMediaClient.authUsuario(user.getUsername())) {
			ret.setResponse(AuthResponse.FAILED);
			ret.setUsuario(null);
		} else {
			if (usuEJB.registrarPrimerIngreso(user, tipoUser))
				ret.setResponse(AuthResponse.PRIMERINGRESO);
			else
				ret.setResponse(AuthResponse.OK);
			
			ret.setUsuario(usuDAO.findByUsername(user.getUsername()));
			userConectados.put(user.getUsername(),user);
		}
		return ret;
	}

	@Override
	public void validarDatosConRedes(Usuario user) {
		Usuario uLogueado = getUsuarioLogueado(user.getUsername());
		
		if (uLogueado != null) {
			
			uLogueado.setPrimerIngreso(false);
			uLogueado.setNombre(user.getNombre());
			uLogueado.setApellido(user.getApellido());
			uLogueado.setCorreo(user.getCorreo());
			uLogueado.setDireccion(user.getDireccion());
			uLogueado.setFechaNacimiento(user.getFechaNacimiento());
			uLogueado.setNacionalidad(user.getNacionalidad());
		
			usuEJB.actualizarDatos(uLogueado);
		}		
	}
	
	@Override
	public Usuario getUsuarioLogueado(String username) {
		return userConectados.get(username);
	}

	@Override
	public void cerrarSesion(String username) {
		
		Usuario u = userConectados.get(username);
		userConectados.remove(username, u);
		
	}
	
}
