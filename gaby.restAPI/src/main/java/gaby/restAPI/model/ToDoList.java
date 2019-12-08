package gaby.restAPI.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ToDoList {

	private String value;
	private List<String> resources;
	
	public ToDoList(){
		value=null;
		resources=new ArrayList<String>();
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<String> getResources() {
		return resources;
	}
	public void setResources(List<String> resources) {
		this.resources = resources;
	}
	
	public void addResource(String str) {
		this.resources.add(str);
	}
	
	@Override
	public String toString(){
		String temp=new String();
		for(String res : resources) {
			temp+=" value = " +res;
		}
		return temp;
	}
}
