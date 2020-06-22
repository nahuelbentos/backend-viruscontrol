package uy.viruscontrol.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.interfaces.FirmaDigitalLocal;

@WebServlet("/FirmarPdfTest")
public class FirmarPdfTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB private FirmaDigitalLocal beanFirma;
    
    public FirmarPdfTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		beanFirma.firmarPdf("/home/jhonatan/reportes/resultado_100.pdf");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
