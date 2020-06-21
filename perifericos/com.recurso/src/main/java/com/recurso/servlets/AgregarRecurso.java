package com.recurso.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recurso.model.HandlerModel;
import com.recurso.model.entities.Recurso;

/**
 * Servlet implementation class AgregarRecurso
 */
@WebServlet(name = "agregarrecurso", description = "Agregar recurso al proveedor logueado", urlPatterns = { "/agregarrecurso" })
public class AgregarRecurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB HandlerModel manejador;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarRecurso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Recurso> recursos = manejador.getAllRecursos();
		
		request.setAttribute("recursos", recursos);
		
		request.getRequestDispatcher("/agregarRecurso.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
