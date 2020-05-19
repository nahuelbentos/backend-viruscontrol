package uy.viruscontrol.servlets;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.enumerated.AuthResponse;
import uy.viruscontrol.bussines.enumerated.TipoUsuario;
import uy.viruscontrol.bussines.interfaces.SessionBeanLocal;
import uy.viruscontrol.model.dao.interfaces.CiudadanoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.PrestadoraSaludDAOLocal;
import uy.viruscontrol.model.dao.interfaces.UsuarioDAOLocal;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.PrestadoraSalud;
import uy.viruscontrol.model.entities.Usuario;

@WebServlet("/IniciarSesionTest")
public class IniciarSesionTest extends HttpServlet {

	@EJB SessionBeanLocal sessionEJB;
	@EJB CiudadanoDAOLocal usuDAO;
	@EJB PrestadoraSaludDAOLocal prestadoraDAO;
	
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
		
		Ciudadano jhonnie = (Ciudadano)usuDAO.findById(1);
		System.out.println("Voy a asignarle a " + jhonnie.getUsername() +" una prestadora recien creada.");
		
		PrestadoraSalud mucam = new PrestadoraSalud();
		mucam.setNombre("Medica Uruguaya");
		prestadoraDAO.persist(mucam);
		System.out.println("Quedo agregada la prestadora de salud.");
		
		List<PrestadoraSalud> listPS = prestadoraDAO.findAll();
		PrestadoraSalud getter = listPS.get(0);
		
		System.out.println("Me quede con la prestadora: " + "("+ getter.getId() +")" + getter.getNombre());
		
		jhonnie.setPrestadoraSalud(getter);
		usuDAO.merge(jhonnie);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
