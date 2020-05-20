package uy.viruscontrol.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.serviceagents.ServiceAgentRucafLocal;


@WebServlet("/ServiceAgentRucafTest")
public class ServiceAgentRucafTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @EJB private ServiceAgentRucafLocal saRucaf;
    
    public ServiceAgentRucafTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("ServiceAgentRucaf dice: "+saRucaf.estaDisponible(1));
		System.out.println("ServiceAgentRucaf dice: "+saRucaf.obtenerMedicoAsignado(1));
		System.out.println("ServiceAgentRucaf dice: "+saRucaf.obtenerMedicos(1));
		System.out.println("ServiceAgentRucaf dice: "+saRucaf.obtenerPrestadorDeSalud(1));
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
