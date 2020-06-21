package uy.viruscontrol.utils.firebase;

import java.io.Serializable;

public class NotificationResponseResults implements Serializable {
	private static final long serialVersionUID = -7985038301520373106L;
	
	private String message_id;
	
	public NotificationResponseResults() {
		super();
	}

	public NotificationResponseResults(String message_id) {
		super();
		this.message_id = message_id;
	}

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	@Override
	public String toString() {
		return "{\"message_id\":\"" + message_id + "\"}";
	}

}
