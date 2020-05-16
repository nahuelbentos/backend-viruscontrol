package uy.viruscontrol.bussines;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import uy.viruscontrol.bussines.interfaces.UsuarioBeanLocal;
import uy.viruscontrol.bussines.interfaces.UsuarioBeanRemote;

@Stateless
@LocalBean
public class UsuarioBean implements UsuarioBeanRemote, UsuarioBeanLocal {

    public UsuarioBean() {}

    @EJB
    private 
    
    
	@Override
	public boolean iniciarSesion(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
    
    

}
