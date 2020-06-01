package uy.viruscontrol.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorRecursoLocal;
import uy.viruscontrol.model.entities.ProveedorRecursos;
import uy.viruscontrol.model.entities.Recurso;

/**
 * Servlet implementation class SAgProvRecursos
 */
@WebServlet(name = "SAgProvRecursosTest", urlPatterns = { "/SAgProvRecursosTest" })
public class SAgProvRecursos extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB private ServiceAgentProveedorRecursoLocal sagProvRec;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SAgProvRecursos() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ProveedorRecursos> pprr = sagProvRec.getProveedoresPeriferico();
		
		String codigoPer = "";
		for (ProveedorRecursos proveedorRecursos : pprr) {
			response.getWriter().append("Prov: ").append(proveedorRecursos.getNombre()).append(proveedorRecursos.getCodigoPeriferico()).append('\n');
			codigoPer = proveedorRecursos.getCodigoPeriferico();
		}
		
		List<Recurso> recursosDeProv = sagProvRec.getRecursosProvPeriferico(codigoPer);
		response.getWriter().append("******************* RECURSOS DISPONIBLES DE ").append(codigoPer);
		for (Recurso recurso : recursosDeProv) {
			response.getWriter().append("Rec: ").append(recurso.getNombre()).append('\n');
			System.out.println(recurso.getNombre());
		}
		
	}

}
