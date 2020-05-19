package uy.viruscontrol.servlets;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.bussines.interfaces.SessionBeanLocal;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Usuario;

@WebServlet("/IniciarSesionTest")
public class IniciarSesionTest extends HttpServlet {

	@EJB SessionBeanLocal sessionEJB;
	
	private static final long serialVersionUID = 1L;
       
    public IniciarSesionTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Test CU : iniciarSesionConRedes...
		Usuario ciudadano = new Ciudadano();
		ciudadano.setNombre("Jhonnie");
		ciudadano.setApellido("Tolengo");
		ciudadano.setDireccion("Gral. Flores 3254");
		ciudadano.setFechaNacimiento(new GregorianCalendar(1985, 10, 15));
		ciudadano.setNacionalidad("Uruguayo");
		ciudadano.setCorreo("jhoni@adinet.com.uy");
		ciudadano.setUsername("jhonnie");
		
		AuthResponse res = sessionEJB.iniciarSesionConRedes(ciudadano, TipoUsuario.CIUDADANO);
		
		System.out.println("El Session bean respondio: "+res);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
