package uy.viruscontrol.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorExamenLocal;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.utils.DtExamen;


@WebServlet("/ServiceAgentProveedorExamenTest")
public class ServiceAgentProveedorExamenTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @EJB private ServiceAgentProveedorExamenLocal saProvEx;
    
    public ServiceAgentProveedorExamenTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ServiceAgentProveedorExamen.obtenerProveedor(100) dice "+saProvEx.obtenerProveedor(100).getId() + " " + saProvEx.obtenerProveedor(100).getNombre());
		for (ProveedorExamen it : saProvEx.obtenerProveedores(100))
			System.out.println("ServiceAgentProveedorExamen.obtenerProveedores() dice "+it.getId() + " " + it.getNombre());
		for (DtExamen it : saProvEx.obtenerExamenesParaUnaEnfermedad(100))
			System.out.println("ServiceAgentProveedorExamen.obtenerExamenesParaUnaEnfermedad(100) dice "+it.getNombre());
		System.out.println("ServiceAgentProveedorExamen.altaDeExamen(102, 100, 100) dice "+saProvEx.altaDeExamen(102, 100, 100).getNombre());
		for (int i=1;i<=5;i++)
			System.out.println("ServiceAgentProveedorExamen.obtenerResultadoExamen("+i+") dice "+saProvEx.obtenerResultadoExamen(i));
		
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
