package uy.viruscontrol.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.entities.Usuario;

@WebFilter(urlPatterns = {"/admin/*", "/gerente/*", "/home.xhtml"})
public class SessionFilter implements Filter {
	
	private ServletContext ctx;
	
	public void init(FilterConfig conf) throws ServletException {
		this.ctx = conf.getServletContext();
		if (ctx != null)
			this.log("INFO: Filtro de sesión inicializado");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		this.log("INFO: Recurso solicitado: "+uri);
		boolean success = false;
		
		HttpSession session = req.getSession();
		
		// obtengo valores de la sesión
		Usuario currentUser = (Usuario) session.getAttribute("currentUser");
		
		if (currentUser != null) {
			if (currentUser instanceof Administrador && (uri.contains("admin") || uri.endsWith("home.xhtml") || !uri.endsWith("html"))) {
				this.log("INFO: Logueado como administrador. Resolviendo petición");
				success = true;
				chain.doFilter(request, response);
			} else {
				if (currentUser instanceof Gerente && (uri.contains("gerente") || uri.endsWith("home.xhtml") || !uri.endsWith("html"))) {
					this.log("INFO: Logueado como Gerente. Resolviendo petición");
					success = true;
					chain.doFilter(request, response);
				}
			}
			 
			if (!success) {
				this.log("ERROR: Acceso no autorizado.");
				res.sendRedirect((uri.contains("admin") || uri.contains("gerente")) ? "../noAutorizado.xhtml" : "noAutorizado.xhtml");
			}
		} else {
			this.log("ERROR: No autenticado. Redireccionando al login.");
			res.sendRedirect((uri.contains("admin") || uri.contains("gerente")) ? "../login.xhtml" : "login.xhtml");
		}
		
	}
	
	private void log(String msg) {
		//System.out.println("["+getClass().getCanonicalName()+"] "+msg);
	}

}
