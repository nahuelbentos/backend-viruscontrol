package uy.viruscontrol.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.MedicoBean;
import uy.viruscontrol.bussines.interfaces.MedicoBeanLocal;
import uy.viruscontrol.bussines.serviceagents.ServiceAgentProveedorExamenLocal;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.model.entities.ProveedorExamen;
import uy.viruscontrol.utils.DtExamen;


@WebServlet("/ServiceAgentProveedorExamenTest")
public class ServiceAgentProveedorExamenTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @EJB private ServiceAgentProveedorExamenLocal saProvEx;
    
    @EJB private MedicoBeanLocal medicoBean;
    
    public ServiceAgentProveedorExamenTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//http://localhost:8080/viruscontrol-web/ServiceAgentProveedorExamenTest
		System.out.println("ServiceAgentProveedorExamen.obtenerResultadoExamen dice " + saProvEx.obtenerResultadoExamen(1));
		System.out.println("aca 1");
	//	List<DtExamen> listEx = saProvEx.obtenerExamenesParaUnaEnfermedad(100);
		List<DtExamen> listEx = medicoBean.obtenerExamenesDeEnfermedad(100);

		System.out.println("aca 2");
		if(listEx==null) {
			System.out.println("aca 3");
			System.out.println("listEx= null");
		}
		System.out.println("aca 4");
		
		
		for (DtExamen e : listEx) {
				System.out.println("aca 5");
			System.out.println("Examen: " + e.getId());
			System.out.println("aca 6");
		}
		
		System.out.println("mostrando ciudadanos: ");
		List<Ciudadano> ciudadanos = medicoBean.mostrarCiudadanos();
		for(Ciudadano c:ciudadanos) {
			System.out.println("id : "+c.getIdUsuario());
			System.out.println("nombre: "+c.getNombre());
			System.out.println("-------------------");
		}
		
		System.out.println("mostrar proveedores");
		List<ProveedorExamen> listProv=medicoBean.ObtenerProveedoresExamen(100); ; //saProvEx.obtenerProveedores(100)
		if(listProv!=null) {
			for(ProveedorExamen p:listProv) {
				System.out.println("id : "+p.getId());
				System.out.println("nombre : "+p.getNombre());
				System.out.println("barrio: "+p.getBarrio());
				System.out.println("rango horario: "+p.getRangoHorario());
				System.out.println("-------------");
			}
		}
		
		medicoBean.nuevoCaso(100, 100, 100,102,103);
		
		/*System.out.println("aca 7");
		DtExamen solEx = saProvEx.altaDeExamen(1, 1, 2);
		System.out.println("aca 8");
		System.out.println("ServiceAgentProveedorExamen.altaDeExamen dice " + solEx.getId() + " " + solEx.getIdEnfermedad() + " " + solEx.getNombreEnfermedad());
		*/
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
