package uy.viruscontrol.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorExamenLocal;
import uy.viruscontrol.utils.DtExamen;


@WebServlet("/ServiceAgentProveedorExamenTest")
public class ServiceAgentProveedorExamenTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @EJB private ServiceAgentProveedorExamenLocal saProvEx;
    
    public ServiceAgentProveedorExamenTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ServiceAgentProveedorExamen.obtenerResultadoExamen dice " + saProvEx.obtenerResultadoExamen(1));
		List<DtExamen> listEx = saProvEx.obtenerExamenesParaUnaEnfermedad(100);
		for (DtExamen e : listEx) {
			System.out.println("Examen: " + e.getId());
		}
		
		DtExamen solEx = saProvEx.altaDeExamen(1, 1, 2);
		System.out.println("ServiceAgentProveedorExamen.altaDeExamen dice " + solEx.getId() + " " + solEx.getIdEnfermedad() + " " + solEx.getNombreEnfermedad());
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
