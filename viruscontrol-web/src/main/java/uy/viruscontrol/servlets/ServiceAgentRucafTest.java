package uy.viruscontrol.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.serviceagents.ServiceAgentPrestadoraSaludLocal;


@WebServlet("/ServiceAgentRucafTest")
public class ServiceAgentRucafTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @EJB private ServiceAgentPrestadoraSaludLocal saRucaf;
    
    public ServiceAgentRucafTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServiceAgentRucaf dice la prestadora de salud del usuario 33333333 es: "+saRucaf.obtenerPrestadorDeSalud(33333333).getNombre());
		System.out.println("ServiceAgentRucaf dice la prestadora de salud del usuario 58966535 es: "+saRucaf.obtenerPrestadorDeSalud(58966535).getNombre());
		System.out.println("ServiceAgentRucaf dice la prestadora de salud del usuario 42366591 es: "+saRucaf.obtenerPrestadorDeSalud(42366591).getNombre());
		System.out.println("ServiceAgentRucaf dice la prestadora de salud del usuario 25699855 es: "+saRucaf.obtenerPrestadorDeSalud(25699855).getNombre());
		System.out.println("ServiceAgentRucaf dice la prestadora de salud del usuario 36655589 es: "+saRucaf.obtenerPrestadorDeSalud(36655589).getNombre());
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
