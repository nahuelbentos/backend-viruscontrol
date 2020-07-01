package uy.viruscontrol.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uy.viruscontrol.bussines.serviceagents.ServiceAgentFirebaseLocal;
import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.CiudadanoDAOLocal;
import uy.viruscontrol.model.entities.Ciudadano;
import uy.viruscontrol.utils.firebase.NotificationInfo;
import uy.viruscontrol.utils.firebase.NotificationInfoData;
import uy.viruscontrol.utils.firebase.NotificationPriority;


@WebServlet("/ServiceAgentFirebaseTest")
public class ServiceAgentFirebaseTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB private CasoDAOLocal daoCaso;
    @EJB private ServiceAgentFirebaseLocal saFirebase;
    @EJB private CiudadanoDAOLocal daoCiudadano;
    
    public ServiceAgentFirebaseTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idC = Integer.parseInt(request.getParameter("idC"));
		Ciudadano c = daoCiudadano.findById(idC);
		if (c != null && c.getTokenPushNotifications() != null && !c.getTokenPushNotifications().equals("")) {
			NotificationInfo notificacion = new NotificationInfo(c.getTokenPushNotifications(), NotificationPriority.normal,
							new NotificationInfoData("VirusControlUY", 
													"Prueba de Notificaciones Push mediante Firebase"));
			saFirebase.sendPushNotification(notificacion);
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
