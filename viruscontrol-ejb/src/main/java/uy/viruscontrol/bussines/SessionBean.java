package uy.viruscontrol.bussines;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
import uy.viruscontrol.security.TokensBeanLocal;
import uy.viruscontrol.security.UserAuthFE;

@Singleton
@LocalBean
public class SessionBean implements SessionBeanRemote, SessionBeanLocal {
	
	@EJB private UsuarioBeanLocal usuEJB;
	@EJB private ProxyRedesSocialesLocal socialMediaClient;
	@EJB private UsuarioDAOLocal usuDAO;
	@EJB private TokensBeanLocal beanToken;
	
	private Map<String,Usuario> userConectados = new HashMap<String, Usuario>();
	
	public SessionBean() {}

	@Override
	public AuthResponse iniciarSesion(String username, String password) {
		
		if (usuEJB.autenticarUsuario(username, password)) {
			Usuario usu = usuEJB.getUsuario(username);
			userConectados.put(beanToken.getToken(usu), usu);
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
			ret.setSessionToken("");
		} else {
			if (usuEJB.registrarPrimerIngreso(user, tipoUser))
				ret.setResponse(AuthResponse.PRIMERINGRESO);
			else
				ret.setResponse(AuthResponse.OK);
			
			ret.setUsuario(usuDAO.findByUsername(user.getUsername()));
			ret.setSessionToken(beanToken.getToken(user));
			
			userConectados.put(beanToken.getToken(user),user);
		}
		return ret;
	}

	@Override
	public void validarDatosConRedes(Usuario user) {
		
		Usuario uLogueado = getUsuarioLogueado(getTokenByUsername(user.getUsername()));
		
		if (uLogueado != null) {
			
			uLogueado.setPrimerIngreso(false);
			uLogueado.setNombre(user.getNombre());
			uLogueado.setApellido(user.getApellido());
			uLogueado.setCorreo(user.getCorreo());
			uLogueado.setDireccion(user.getDireccion());
			uLogueado.setFechaNacimiento(user.getFechaNacimiento());
			uLogueado.setNacionalidad(user.getNacionalidad());
			uLogueado.setDocumento(user.getDocumento());
			uLogueado.setNroTelefono(user.getNroTelefono());
		
			usuEJB.actualizarDatos(uLogueado);
		}		
	}
	
	@Override
	public Usuario getUsuarioLogueado(String token) {
		return userConectados.get(token);
	}
	
	@Override
	public String getTokenByUsername(String username) {
		/* No usar esta función. Solamente se deberá usar desde el backoffice. 
		 * Desde otro lugar, siempre obtener por token
		 */
		for(Entry<String, Usuario> it : userConectados.entrySet()) {
			if (it.getValue().getUsername().equals(username))
				return it.getKey();
		}
		return "";
	}

	@Override
	public void cerrarSesion(String token) {
		Usuario u = userConectados.get(token);
		userConectados.remove(token, u);
	}
	
	@Override
	public boolean validateAuthentication(String token) {
		Usuario u = userConectados.get(token);
		if (u != null) {
			String tmpToken = beanToken.getToken(u);
			return token.equals(tmpToken);
		} else
			return false;
	}
}
