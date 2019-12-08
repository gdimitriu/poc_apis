package gaby.restAPI.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ToDoUserResponse {

	private String userid;
	private String token;
	
	public ToDoUserResponse(){
		this.userid=null;
		this.token=null;
	}
	
	public ToDoUserResponse(String userid,String token){
		this.token=token;
		this.userid=userid;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString(){
		return new String("userId=" + this.userid + " : token="+ this.token);
	}
	
}
