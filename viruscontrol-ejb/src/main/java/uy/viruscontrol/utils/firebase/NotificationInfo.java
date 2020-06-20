package uy.viruscontrol.utils.firebase;

import java.io.Serializable;

public class NotificationInfo implements Serializable {
	private static final long serialVersionUID = -8766113300378741799L;
	
	private String to;
	private NotificationPriority priority;
	private NotificationInfoData data;
	
	public NotificationInfo() {
		super();
	}

	public NotificationInfo(String to, NotificationPriority priority, NotificationInfoData data) {
		super();
		this.to = to;
		this.priority = priority;
		this.data = data;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public NotificationPriority getPriority() {
		return priority;
	}

	public void setPriority(NotificationPriority priority) {
		this.priority = priority;
	}

	public NotificationInfoData getData() {
		return data;
	}

	public void setData(NotificationInfoData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "NotificationInfo [to=" + to + ", priority=" + priority + ", data=" + data + "]";
	}

}
