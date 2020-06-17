package uy.viruscontrol.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.interfaces.GerenteBeanLocal;

/**
 * Servlet implementation class GerenteBeanTest
 */
@WebServlet("/GerenteBeanTest")
public class GerenteBeanTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB GerenteBeanLocal gerenteBean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenteBeanTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//http://localhost:8080/viruscontrol-web/GerenteBeanTest
		
		gerenteBean.mandarMail("javierms17@gmail.com","java","mensaje enviado desde java");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
