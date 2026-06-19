package gaby.jersey.jaxb.resources;

import gaby.jersey.jaxb.model.Todo;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/todo")
public class TodoResource {
  // This method is called if XMLis request
  @GET
  @Produces({ MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
  public Todo getXML() {
    Todo todo = new Todo();
    todo.setSummary("This is my first todo");
    todo.setDescription("This is my first todo");
    return todo;
  }
  
  // This can be used to test the integration with the browser
  @GET
  @Produces({ MediaType.TEXT_XML })
  public Todo getHTML() {
    Todo todo = new Todo();
    todo.setSummary("This is my first todo");
    todo.setDescription("This is my first todo");
    return todo;
  }
  
  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public void postUpgradeData(@FormDataParam("Name") String name, @FormDataParam("data") String xmlData) {
	  System.out.println(name + ":" + xmlData);
  }

} 