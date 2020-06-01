package uy.viruscontrol.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.ProveedorBean;
import uy.viruscontrol.bussines.interfaces.ProveedorBeanLocal;

/**
 * Servlet implementation class ProveedorBeanTest
 */
@WebServlet("/ProveedorBeanTest")
public class ProveedorBeanTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       @EJB ProveedorBeanLocal proveedorBeanLocal;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProveedorBeanTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		proveedorBeanLocal.nuevoProveedor(1, "farmashop", "18 y andes", "centro","24hs",null);
		proveedorBeanLocal.nuevoProveedor(2, "proveEx", "cochabamba12", "malvin", "15hs",null);
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
