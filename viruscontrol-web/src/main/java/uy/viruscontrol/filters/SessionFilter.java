package uy.viruscontrol.filters;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uy.viruscontrol.ui.views.UserManager;

@WebFilter
public class SessionFilter implements Filter {

	public static final String LOGIN_PAGE = "/login.xhtml";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

        
		
		//FacesContext context = FacesContext.getCurrentInstance();
		//HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
		//UserManager user = (UserManager)session.getAttribute("UserManager"); 
		
		/* EL FILTRO NO OBTIENE CORRECTAMENTE EL USUARIO LOGUEADO.
    	System.out.println("verifico si esta logueado: " + user.isLoggedIn());
		if (user != null && user.isLoggedIn()) {
    		chain.doFilter(request, response);
    	} else {
    		HttpServletRequest sendRedReq = (HttpServletRequest)request;
    		HttpServletResponse sendRedRes = (HttpServletResponse)response;
    		sendRedRes.sendRedirect(sendRedReq.getContextPath() + LOGIN_PAGE);
    	}
    	*/
	}
	

}
