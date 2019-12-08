package gaby.restAPI.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ToDoUser {
	private String user;
	private String password;
	private String name;
	
	public ToDoUser(){
		this.user=null;
		this.password=null;
		this.name=null;
	}
	
	public ToDoUser(String name,String user, String password){
		this.name=name;
		this.user=user;
		this.password=password;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return new String("Name=" +  this.name + " : User="+ this.user + " : Password="+ this.password);
	}
}
