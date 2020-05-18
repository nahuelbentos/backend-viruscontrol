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
import uy.viruscontrol.model.entities.Usuario;
import uy.viruscontrol.proxies.ProxyRedesSocialesLocal;

@Singleton
@LocalBean
public class SessionBean implements SessionBeanRemote, SessionBeanLocal {

	@EJB
	private UsuarioBeanLocal usuEJB;
	@EJB
	private ProxyRedesSocialesLocal socialMediaClient;
	
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
	public AuthResponse iniciarSesionConRedes(Usuario user, TipoUsuario tipoUser) {
		AuthResponse res;
		if (!socialMediaClient.authUsuario(user.getUsername()))
			res = AuthResponse.FAILED;
		else {
			if (usuEJB.registrarPrimerIngreso(user, tipoUser))
				res = AuthResponse.PRIMERINGRESO;
			else
				res = AuthResponse.OK;
			userConectados.put(user.getUsername(),user);
		}
		return res;
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
	
	private Usuario getUsuarioLogueado(String username) {
		return userConectados.get(username);
	}
	
}
