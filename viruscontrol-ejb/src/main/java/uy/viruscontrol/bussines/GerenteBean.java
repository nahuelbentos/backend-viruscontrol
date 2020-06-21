package uy.viruscontrol.bussines;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.MediaType;

import uy.viruscontrol.bussines.enumerated.TipoNotificacion;
import uy.viruscontrol.bussines.interfaces.GerenteBeanLocal;
import uy.viruscontrol.bussines.interfaces.GerenteBeanRemote;
import uy.viruscontrol.model.dao.impl.ConfiguracionNotificacionesDAO;
import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.dao.interfaces.ConfiguracionNotificacionesDAOLocal;
import uy.viruscontrol.model.entities.Caso;
import uy.viruscontrol.model.entities.ConfiguracionNotificaciones;

/**
 * Session Bean implementation class GerenteBean
 */
@Stateful
@LocalBean
public class GerenteBean implements GerenteBeanRemote, GerenteBeanLocal {

	@EJB private CasoDAOLocal casoDao;
	@EJB private ConfiguracionNotificacionesDAOLocal confDao;
	
    /**
     * Default constructor. 
     */
    public GerenteBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void mandarMail(String receptor,String asunto,String mensaje) {
    	List<String> receptores = new ArrayList<String>();
    	receptores.add(receptor);
    	this.sendMail(receptores, asunto, mensaje);
    }
    
    @Override
    public void mandarMail(List<String> receptores,String asunto,String mensaje) {
    	this.sendMail(receptores, asunto, mensaje);
    }
    
    private void sendMail(List<String> receptores,String asunto,String mensaje) {
    	//gmail tiene una opcion para dar acceso a apps para poder mandar mail:
    	//https://myaccount.google.com/lesssecureapps
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth","true");
        

        
        Session sesion = Session.getDefaultInstance(propiedad);
        String correoEnvia = "javierms17dos@gmail.com";
        String contrasena = "12548175309271";
		/*
		 * String receptor = "javierms17@gmail.com"; String asunto = "hola"; String
		 * mensaje="enviado desde java";
		 */
        
        MimeMessage mail = new MimeMessage(sesion);
        try {
            mail.setFrom(new InternetAddress (correoEnvia));
            mail.setSubject(asunto);
            //mail.setText(mensaje);
            String htmlMessage = this.htmlFormatMessage(mensaje);
            
            for (String receptor : receptores)
            	mail.addRecipient(Message.RecipientType.TO, new InternetAddress (receptor));
            
            
            mail.setContent(htmlMessage, MediaType.TEXT_HTML);
            
            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(correoEnvia,contrasena);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));          
            transportar.close();
            
           
            
            
        } catch (AddressException ex) {
            System.out.println("error al mandar mail");
        } catch (MessagingException ex) {
           System.out.println("error al mandar mail");
        }
    }
    
    private String htmlFormatMessage(String mensaje) {
    	return "<html>"+
    			"	<head>"+
    			"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\">"+
    			"	</head>"+
    			"	<body>"+
    					mensaje+
    			"	</body>"+
    			"</html>";
    }
   
    @Override
    public List<Caso> obtenerCasos(){
    	return casoDao.findAll();
    }
    
    @Override
    public void configurarNotificacion(boolean notificarCiudadano,boolean notificarMedico,boolean notificarGerentes,TipoNotificacion tipo) {
    	
    	ConfiguracionNotificaciones confNot=confDao.findById(tipo);
    	if(confNot!=null) {
    		
    		confNot.setNotificarCiudadano(notificarCiudadano);

    		confNot.setNotificarGerentes(notificarGerentes);

    		confNot.setNotificarMedico(notificarMedico);
    		
    		confDao.merge(confNot);
    		
    	}
    		
    	
    }
    
    @Override
    public List<ConfiguracionNotificaciones> obtenerConfuracionNotificacion() {
    	
    	return confDao.findAll();
    	
    	
    }
    
}
