package uy.viruscontrol.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.interfaces.EnfermedadBeanLocal;
import uy.viruscontrol.model.dao.interfaces.EnfermedadDAOLocal;
import uy.viruscontrol.model.dao.interfaces.SintomaDAOLocal;
import uy.viruscontrol.model.entities.Enfermedad;
import uy.viruscontrol.model.entities.Sintoma;

/**
 * Servlet implementation class EnfermedadTest
 */
@WebServlet("/EnfermedadTest")
//http://localhost:8080/viruscontrol-web/EnfermedadTest
public class EnfermedadTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@EJB EnfermedadBeanLocal enfermedadBeanLocal;
    @EJB SintomaDAOLocal daoSintomaLocal;
    @EJB EnfermedadDAOLocal daoEnfermedadLocal;
    public EnfermedadTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Sintoma>sintomas = new ArrayList<Sintoma>();
		sintomas = daoSintomaLocal.findAll();
		
		Sintoma s = new Sintoma("Sintoma Test");
		sintomas.add(s);
		
		//SE CREA ENFERMEDAD, TIPOENFERMEDAD NO EXISTE, SINTOMAS EXISTEN + 1 SINTOMA NUEVO, CASO POSITIVO
		boolean casoPositivo = enfermedadBeanLocal.crearEnfermedadInfecciosa("Enfermedad Test", "Tipo Test", "Agente Test", sintomas, false, Float.valueOf(0));
		//SE CREA ENFERMEDAD CASO NEGATIVO
		Enfermedad e = new Enfermedad();
		e=daoEnfermedadLocal.findById(100);
		boolean casoNegativo = enfermedadBeanLocal.crearEnfermedadInfecciosa(e.getNombre(),e.getTipoEnfermedad().getNombre(),e.getNombreAgente(), e.getSintomas(), false, Float.valueOf(0));
		
		
		//SE APRUEBA ENFERMEDAD CREADA
		boolean aprueba = enfermedadBeanLocal.aprobarEnfermedadInfecciosa(enfermedadBeanLocal.getIdEnfermedadByName("Enfermedad Test"));
		
		
		if(casoPositivo)
			System.out.println("SE CREÓ LA ENFERMEDAD DE NOMBRE Enfermedad Test, SE CREÓ TIPO DE NOMBRE Tipo Test Y SE CREÓ SNTOMA DE NOMBRE Sintoma Test");
		else
			System.out.println("EL TEST FALLÓ, NO SE CREÓ LA ENFERMEDAD DE NOMBRE Enfermedad Test");
		
		
		if(!casoNegativo)
			System.out.println("RESULTADO CORRECTO, ENFERMEDAD DE NOMBRE "+e.getNombre()+"YA EXISTE");
		else
			System.out.println("EL TEST FALLÓ");
		
		
		if(aprueba)
			System.out.println("SE APROBÓ LA ENFERMEDAD DE NOMBRE Enfermedad Test");
		else
			System.out.println("EL TEST FALLÓ, NO SE APROBÓ LA ENFERMEDAD DE NOMBRE Enfermedad Test");
		
		
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
