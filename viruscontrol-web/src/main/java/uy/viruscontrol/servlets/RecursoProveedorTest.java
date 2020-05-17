package uy.viruscontrol.servlets;



import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.model.dao.interfaces.ProveedorRecursoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.RecursoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.RecursoProveedorDAOLocal;
import uy.viruscontrol.model.entities.IdRecursoProveedor;
import uy.viruscontrol.model.entities.ProveedorRecursos;
import uy.viruscontrol.model.entities.Recurso;
import uy.viruscontrol.model.entities.RecursoProveedor;


@WebServlet("/RecursoProveedorTest")
public class RecursoProveedorTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB RecursoProveedorDAOLocal daoRecursoProveedor;
    @EJB RecursoDAOLocal daoRecurso;
    @EJB ProveedorRecursoDAOLocal daoProveedor;
	
	
	
    public RecursoProveedorTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idRecurso = 100;
		int idProvRec = 1;
		
		if (daoRecursoProveedor.findAll().isEmpty()) {
			System.out.println("NO EXISTEN RECURSOS DE NINGUN PROVEEDOR");
		}
		
		Recurso r = daoRecurso.findById(idRecurso);
		if (r == null)
			System.out.println("NO SE ENCONTRÓ EL RECURSO " + idRecurso + ". NO SE PUEDE CONTINUAR CON LA PRUEBA");
		else {
			ProveedorRecursos prov = daoProveedor.findById(idProvRec);
			
			if (prov == null)
				System.out.println("NO SE ENCONTRÓ EL PROVEEDOR DE RECURSOS " + idProvRec + ". NO SE PUEDE CONTINUAR CON LA PRUEBA");
			else {
				
				RecursoProveedor recProv = new RecursoProveedor(r, prov, 10000);
				
				daoRecursoProveedor.persist(recProv);
				
				IdRecursoProveedor id = new IdRecursoProveedor(r, prov);
				RecursoProveedor sel = daoRecursoProveedor.findById(id);
				
				if (sel == null)
					System.out.println("NO SE HA PODIDO ENCONTRAR NINGUN RECURSO CON ID " + idRecurso + "PARA EL PROVEEDOR " + idProvRec + ". VERIFICAR LA OPERACION DEL DAO findById");
				else
					System.out.println("El proveedor "+idProvRec+" cuenta con un stock de "+Float.toString(sel.getCantidad())+" del producto con id "+idRecurso);
				
			}
		}
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
