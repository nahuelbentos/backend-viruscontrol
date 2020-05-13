package uy.viruscontrol.servlets;



import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.model.dao.interfaces.TipoEnfermedadDAOLocal;
import uy.viruscontrol.model.entities.TipoEnfermedad;

/**
 * Servlet implementation class TipoEnfermedadTest
 */

/*
 * 
 *URL http://localhost:8080/viruscontrol-web/TipoEnfermedadTest
 * 
 * 
 * */

@WebServlet("/TipoEnfermedadTest")
public class TipoEnfermedadTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	@EJB TipoEnfermedadDAOLocal daoTipoEnfermedad;
	
	
	
    public TipoEnfermedadTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		///TESTING DAO, EJB, INTERFAZ Y ENTIDAD
		
		if(daoTipoEnfermedad.findAll().isEmpty()) {
			System.out.println("No hay Tipos de Enfermedades en la base");
		}
		
		System.out.println("TESTEANDO TIPOENFERMEDAD.JAVA");
		//TipoEnfermedad.java
		TipoEnfermedad te1 = new TipoEnfermedad();
		te1.setNombre("TipoEnfermedad Test 1");
		
		TipoEnfermedad te2 = new TipoEnfermedad();
		te2.setNombre("TipoEnfermedad Test 2");
		
		System.out.println("TESTEANDO DAO - PERSIST");
		//DAO - persist
		daoTipoEnfermedad.persist(te1);
		daoTipoEnfermedad.persist(te2);
		
		System.out.println("TESTEANDO DAO - FINDALL");
		//DAO - findAll
		if(!daoTipoEnfermedad.findAll().isEmpty()) {
			System.out.println("Hay"+daoTipoEnfermedad.findAll().size()+" Tipos de Enfermedades en la base");
			
		}else {
			System.out.println("Aún no hay Tipos de Enfermedades en la base");
		}
		
		System.out.println("TESTEANDO DAO - DELETE");
		//DAO - delete
		daoTipoEnfermedad.delete(te2);
		
		System.out.println("Hay"+daoTipoEnfermedad.findAll().size()+" Tipos de Enfermedades en la base");
		
		
		System.out.println("TESTEANDO DAO - MERGE");
		//DAO - merge
		te1.setNombre("TipoEnfermedad editado");
		daoTipoEnfermedad.merge(te1);
		
		System.out.println("TESTEANDO DAO - FINDBYID");
		//DAO - findById
		TipoEnfermedad te1Persistido = new TipoEnfermedad();
		te1Persistido = daoTipoEnfermedad.findById(te1.getid());
		System.out.println(te1Persistido.toString());
		
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
