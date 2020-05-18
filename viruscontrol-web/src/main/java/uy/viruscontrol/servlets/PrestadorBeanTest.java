package uy.viruscontrol.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.PrestadorBean;
import uy.viruscontrol.bussines.interfaces.PrestadorBeanLocal;

/**
 * Servlet implementation class PrestadorBeanTest
 */
@WebServlet("/PrestadorBeanTest")
public class PrestadorBeanTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB PrestadorBeanLocal prestadorBean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrestadorBeanTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("creando prestadora carlitox");
		prestadorBean.nuevoPrestador("carlitox");
		System.out.println("creando prestadora alejo");
		prestadorBean.nuevoPrestador("alejo");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
