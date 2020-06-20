package uy.viruscontrol.bussines.serviceagents;

import javax.ejb.Local;

import uy.viruscontrol.utils.firebase.NotificationInfo;

@Local
public interface ServiceAgentFirebaseLocal {
	public void sendPushNotification(NotificationInfo notificacion);
}
