package uy.viruscontrol.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.serviceagents.ServiceAgentPrestadoraSaludLocal;

@WebServlet("/PerifericoPrestadoraSaludDataTest")
public class PerifericoPrestadoraSaludDataTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB private ServiceAgentPrestadoraSaludLocal prestadora;
	
    public PerifericoPrestadoraSaludDataTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("ServiceAgentPrestadoraSalud.estaDisponible(101): "+prestadora.estaDisponible(101));
		System.out.println("ServiceAgentPrestadoraSalud.obtenerMedicoAsignado(100): "+prestadora.obtenerMedicoAsignado(100));
		System.out.println("ServiceAgentPrestadoraSalud.obtenerMedicos(101): "+prestadora.obtenerMedicos(101));
		System.out.println("ServiceAgentPrestadoraSalud.obtenerPrestadorDeSalud(33333333): "+prestadora.obtenerPrestadorDeSalud(33333333));
		System.out.println("ServiceAgentPrestadoraSalud.obtenerPrestadorDeSaludAlternativo(102): "+prestadora.obtenerPrestadorDeSaludAlternativo(102));
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
