package uy.viruscontrol.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SetSessionUser")
public class SetSessionUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SetSessionUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.getSession().setAttribute("UsuarioLogueado", request.getParameter("username"));
	}

}
