package uy.viruscontrol.servlets;


import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.interfaces.UsuarioBeanLocal;
import uy.viruscontrol.model.entities.Administrador;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Gerente;
import uy.viruscontrol.model.entities.Medico;

/**
 * Servlet implementation class UsuarioBeanTest
 */
@WebServlet("/UsuarioBeanTest")
public class UsuarioBeanTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       @EJB UsuarioBeanLocal usuarioBean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioBeanTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Ciudadano> ciudadanos=usuarioBean.mostrarCiudadanos();
		List<Medico> medicos=usuarioBean.mostrarMedicos();
		List<Gerente> gerenetes=usuarioBean.mostrarGerentes();
		List<Administrador> admins=usuarioBean.mostrarAdministradores();
		
		System.out.println("mostrando ciudadanos");
		for(Ciudadano c: ciudadanos) {
		
			System.out.println("nombre: "+c.getNombre());
			System.out.println("apellido: "+c.getApellido());
			System.out.println("------------");
		}
		System.out.println("mostrando medicos");
		
		for(Medico m:medicos) {
			System.out.println("nombre: "+m.getNombre());
			System.out.println("apellido: "+m.getApellido());
			System.out.println("----------------");
		}
		System.out.println("mostrando gerentes");
		for(Gerente g:gerenetes) {
			System.out.println("nombre: "+g.getNombre());
			System.out.println("apellido: "+g.getApellido());
			System.out.println("----------------");
		}
		System.out.println("mostrando administradores");
		for(Administrador a:admins) {
			System.out.println("nombre: "+a.getNombre());
			System.out.println("apellido: "+a.getApellido());
			
		}
		
		
		
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
