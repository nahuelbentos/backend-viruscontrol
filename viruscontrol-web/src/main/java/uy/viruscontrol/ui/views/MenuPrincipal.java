package uy.viruscontrol.ui.views;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("MenuPrincipal")
@SessionScoped
public class MenuPrincipal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserManager userManage;
	
	
	

	public UserManager getUserManage() {
		return userManage;
	}
	public MenuPrincipal(UserManager userManage) {
		super();
		this.userManage = userManage;
	}
	public MenuPrincipal() {
		super();
	}
	
	
	
}
