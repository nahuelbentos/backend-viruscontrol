package uy.viruscontrol.bussines;


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

import uy.viruscontrol.bussines.interfaces.GerenteBeanLocal;
import uy.viruscontrol.bussines.interfaces.GerenteBeanRemote;
import uy.viruscontrol.model.dao.interfaces.CasoDAOLocal;
import uy.viruscontrol.model.entities.Caso;

/**
 * Session Bean implementation class GerenteBean
 */
@Stateful
@LocalBean
public class GerenteBean implements GerenteBeanRemote, GerenteBeanLocal {

	@EJB private CasoDAOLocal casoDao;
	
    /**
     * Default constructor. 
     */
    public GerenteBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void mandarMail(String receptor,String asunto,String mensaje) {
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
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress (receptor));
            mail.setSubject(asunto);
            mail.setText(mensaje);
            
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
    
   
    @Override
    public List<Caso> obtenerCasos(){
    	return casoDao.findAll();
    }
    

}
