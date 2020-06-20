package uy.viruscontrol.bussines.serviceagents;

import java.io.IOException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import com.fasterxml.jackson.databind.ObjectMapper;

import uy.viruscontrol.utils.firebase.NotificationInfo;
import uy.viruscontrol.utils.firebase.NotificationResponse;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

@Stateless
@Local(ServiceAgentFirebaseLocal.class)
public class ServiceAgentFirebase implements ServiceAgentFirebaseLocal {
	private static final String firebaseUrl = "https://fcm.googleapis.com/";
	private static final Header authorization = new BasicHeader("Authorization", "key=AAAAfpO-Mt0:APA91bHHPJ8mU-CBQO2-7P2LDat6RgtE-zIrkyOsCl5g6hPwSpi2HKYH0hCcyRiQlRQEzSg2tKtNTQmsnl80lu0JuyCrLzZry825artpsyRRIoa6eCT4SZbQzEUetvLQB5ThTgzQlIyB");
	private static final Header contenttype = new BasicHeader("Content-Type", MediaType.APPLICATION_JSON);
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public ServiceAgentFirebase() {
		super();
	}

	@Override
	public void sendPushNotification(NotificationInfo laNotificacion) {
		// Método @POST - Uso HttpPost - Devuelve JSON
		HttpClient client = HttpClients.createDefault();
		HttpPost postRequest = new HttpPost(firebaseUrl + "fcm/send");
		postRequest.addHeader(authorization);
		postRequest.addHeader(contenttype);
		
		try {
			EntityBuilder builder = EntityBuilder.create();
			builder.setText(mapper.writeValueAsString(laNotificacion));
			HttpEntity entity = builder.build();
			postRequest.setEntity(entity);
		    
//	    	System.out.println(mapper.writeValueAsString(laNotificacion));
			HttpResponse res = client.execute(postRequest);
			NotificationResponse notifResp = mapper.readValue(res.getEntity().getContent(), NotificationResponse.class);
			System.out.println("Respuesta de FCM: "+notifResp);
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("["+getClass().getCanonicalName()+"] ERROR: No se pudo enviar la notificacion a Firebase. Habilitar la traza para mas detalles. "+e.getMessage());
		}
	}

}
