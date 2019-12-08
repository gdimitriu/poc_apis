package gaby.restAPI.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ToDoResponse{

	private int status;
	private String message;
	private String resource;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	
	@Override
	  public String toString(){
		return new String("Response= status:" + status + " message:"+message + " resource:"+resource);
	}
}
