package uy.viruscontrol.utils.firebase;

import java.io.Serializable;

public class NotificationInfoData implements Serializable {
	private static final long serialVersionUID = 2979931386388036946L;
	
	private String title;
	private String body;

	public NotificationInfoData() {
		super();
	}

	public NotificationInfoData(String title, String body) {
		super();
		this.title = title;
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "NotificationInfoData [title=" + title + ", body=" + body + "]";
	}

}
