package uy.viruscontrol.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.model.dao.interfaces.FuenteDeDatosDAOLocal;
import uy.viruscontrol.model.entities.FuenteDeDatos;


/**
 * Servlet implementation class FuenteDeDatosTest
 */
@WebServlet("/FuenteDeDatosTest")
public class FuenteDeDatosTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB FuenteDeDatosDAOLocal daoFuente;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FuenteDeDatosTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("testeando FuenteDeDatosDAO");
		FuenteDeDatos fdd=new FuenteDeDatos();
		fdd.setCodigo("10");
		fdd.setUrl("www.twiter.com");
	//	fdd.setId(1);
		
		daoFuente.persist(fdd);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
