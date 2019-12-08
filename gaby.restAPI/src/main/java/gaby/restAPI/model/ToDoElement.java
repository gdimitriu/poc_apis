package gaby.restAPI.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
// JAX-RS supports an automatic mapping from JAXB annotated class to XML and JSON
// Isn't that cool?
public class ToDoElement {
  private String summary;
  private String description;
  private String resource;
  
  public ToDoElement() {
	  resource=null;
	  description=null;
	  summary=null;
  }
  
  public String getSummary() {
    return summary;
  }
  public void setSummary(String summary) {
    this.summary = summary;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getResource() {
	return resource;
  }

  public void setResource(String resource) {
	this.resource = resource;
  }
@Override
  public String toString(){
	  return new String("Summary=" + this.summary + ":Description=" + this.description+" :Resource=" + this.resource);
  }

  
} 