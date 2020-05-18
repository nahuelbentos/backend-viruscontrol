package uy.viruscontrol.servlets;



import java.io.IOException;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.model.dao.interfaces.CiudadanoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.MedicoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.PrestadoraSaludDAOLocal;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.Medico;
import uy.viruscontrol.model.entities.PrestadoraSalud;


@WebServlet("/PerifericoPrestadoraSaludDataTest")
public class PerifericoPrestadoraSaludDataTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @EJB private PrestadoraSaludDAOLocal daoPrestadora;
    @EJB private MedicoDAOLocal daoMedico;
    @EJB private CiudadanoDAOLocal daoCiudadano;
	
	
    public PerifericoPrestadoraSaludDataTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrestadoraSalud p = new PrestadoraSalud();
		p.setNombre("SMI - Servicio Médico Integral");
		daoPrestadora.persist(p);
		
		Medico m = new Medico();
		m.setNombre("Jhonny");
		m.setApellido("Bravo");
		m.setDireccion("Boulevard Artigas 1336");
		m.setFechaNacimiento(Calendar.getInstance());
		m.setNacionalidad("Uruguayo");
		m.setCorreo("jhonny_bravo@uy.com.smi");
		m.setUsername("jbravo");
		m.setPrimerIngreso(false);
		PrestadoraSalud pr = daoPrestadora.findById(1);
//		m.setPrestadoraSalud(pr);
		daoMedico.persist(m);
		
		p = new PrestadoraSalud();
		p.setNombre("Medica Uruguaya");
		daoPrestadora.persist(p);
		
		Ciudadano c = new Ciudadano();
		c.setNombre("Jhonnie");
		c.setApellido("Tolengo");
		c.setDireccion("Boulevard Artigas 1563");
		c.setFechaNacimiento(Calendar.getInstance());
		c.setNacionalidad("Uruguayo");
		c.setCorreo("jhonny_tolengo@algo.com");
		c.setUsername("jtolengo");
		c.setPrimerIngreso(false);
		pr = daoPrestadora.findById(1);
//		m.setPrestadoraSalud(pr);
		daoCiudadano.persist(c);
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
