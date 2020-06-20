package uy.viruscontrol.utils.firebase;

import java.io.Serializable;
import java.util.List;

public class NotificationResponse implements Serializable {
	private static final long serialVersionUID = 311685092934075471L;
	
	private String multicast_id;
    private int success;
    private int failure;
    private int canonical_ids;
    private List<NotificationResponseResults> results;
    
	public NotificationResponse() {
		super();
	}

	public NotificationResponse(String multicast_id, int success, int failure, int canonical_ids,
			List<NotificationResponseResults> results) {
		super();
		this.multicast_id = multicast_id;
		this.success = success;
		this.failure = failure;
		this.canonical_ids = canonical_ids;
		this.results = results;
	}

	public String getMulticast_id() {
		return multicast_id;
	}

	public void setMulticast_id(String multicast_id) {
		this.multicast_id = multicast_id;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public int getFailure() {
		return failure;
	}

	public void setFailure(int failure) {
		this.failure = failure;
	}

	public int getCanonical_ids() {
		return canonical_ids;
	}

	public void setCanonical_ids(int canonical_ids) {
		this.canonical_ids = canonical_ids;
	}

	public List<NotificationResponseResults> getResults() {
		return results;
	}

	public void setResults(List<NotificationResponseResults> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "{\"multicast_id\":\"" + multicast_id + "\", \"success\":\"" + success + "\", \"failure\":\"" + failure
				+ "\", \"canonical_ids\":\"" + canonical_ids + "\", \"results\":" + results + "";
	}

}
