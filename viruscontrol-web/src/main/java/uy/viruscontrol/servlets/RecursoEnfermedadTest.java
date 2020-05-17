package uy.viruscontrol.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.interfaces.EnfermedadBeanLocal;
import uy.viruscontrol.model.dao.interfaces.EnfermedadDAOLocal;
import uy.viruscontrol.model.dao.interfaces.RecursoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.RecursoEnfermedadDAOLocal;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.IdRecursoEnfermedad;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.RecursoEnfermedad;

/**
 * Servlet implementation class RecursoEnfermedadTest
 *///http://localhost:8080/viruscontrol-web/RecursoEnfermedadTest
@WebServlet("/RecursoEnfermedadTest")
public class RecursoEnfermedadTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@EJB EnfermedadBeanLocal enfermedadBean;
	@EJB EnfermedadDAOLocal enfermedadDAO;
	@EJB RecursoDAOLocal recursoDAO;
	@EJB RecursoEnfermedadDAOLocal recEnfDAO;
	@PersistenceContext(unitName = "viruscontrolPersistenceUnit")
    protected EntityManager em;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecursoEnfermedadTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("TESTEANDO altaRecursoRecomendado");
		
		//Enf y Rec EXISTEN
		 /*
		Recurso r = new Recurso();
		Enfermedad e = new Enfermedad();
		
		r=recursoDAO.findById(100);
		e=enfermedadDAO.findById(100);
		
		boolean altaOK = enfermedadBean.altaRecursoRecomendado(e.getNombre(), r.getNombre(), true,true);
		*/
		/////////////////////////////////////////////////
		/*
		//Enf y Rec NO EXISTEN
		
		boolean altaOK = enfermedadBean.altaRecursoRecomendado("test", "test", true,false);
		*/
		
		/////////////////////////////////////////////////

		/*
		//Recurso EXISTE, Enfermedad NO
		 
		Recurso r = new Recurso();
		r=recursoDAO.findById(100);
		boolean altaOK = enfermedadBean.altaRecursoRecomendado("test", r.getNombre(), false,false);		
		*/
		/////////////////////////////////////////////////

		
		//Recurso NO EXISTE, Enfermedad SI
		/*
		Enfermedad e = new Enfermedad();
		e=enfermedadDAO.findById(100);
		
		boolean altaOK = enfermedadBean.altaRecursoRecomendado(e.getNombre(), "test", false,true);
		*/
		////findById
		/*
		Recurso r = new Recurso();
		r=recursoDAO.findById(200);
		Enfermedad e = new Enfermedad();
		e=enfermedadDAO.findById(100);
		RecursoEnfermedad recEnf = new RecursoEnfermedad();
		recEnf= recEnfDAO.findById(r, e);
		//System.out.println("La asociacion con clave compuesta que me traigo de la db es:"+recEnf.getId());
		*/
		//IdRecursoEnfermedad idAux = new IdRecursoEnfermedad(100, 200);
		//recEnf=em.find(RecursoEnfermedad.class, idAux);
		/*
		if(recEnf!=null)
			System.out.println("Todo bien!");
		else 
			System.out.println("Es null");
		
		System.out.println("El recurso tiene id: " + r.getId());
		System.out.println("La enfermedad tiene id: " + e.getId());
		*/
		
		//RESULTADO
		/*
		if(altaOK)
			System.out.println("Todo bien!");
		else
			System.out.println("Todo mal");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		*/
		
		/*
		 * 
		 RECURSO, ENFERMEDAD
		 EXISTE    EXISTE   -------true si la asociacion no existe, true si la asociacion existe (hay que validar si existe clave)
		 EXISTE    NO EXISTE-------false dado que no hay nada que hacer 
		 NO EXISTE EXISTE-------true dado que se da de alta el recurso y se asocia a la enfermedad
		 NO EXISTE  NO EXISTE ----true dado que se crea el recurso sin enfermedad asociada   
		 * 
		 */
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		
	}

}
