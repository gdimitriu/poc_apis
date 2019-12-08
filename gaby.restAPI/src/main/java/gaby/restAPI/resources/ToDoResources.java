package gaby.restAPI.resources;

import gaby.restAPI.exceptions.ResourceNotFoundException;
import gaby.restAPI.model.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.Api;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/rest/todo")
@PermitAll
@Api(value="/rest/todo",description="ToDo resources")
public class ToDoResources {

	//this should be replaced by a factory or @Singleton
	private static Map<String,ToDoElement> resources=new HashMap<String,ToDoElement>();

  // This can be used to test the integration with the browser
  @GET
  @Produces({ MediaType.TEXT_XML })
  public ToDoElement getHTML(@QueryParam("user") final String user,@QueryParam("password") final String password) {
	System.out.println("get xml user="+user + " password="+password);
    ToDoElement todo = new ToDoElement();
    todo.setSummary("This is my first todo");
    todo.setDescription("This is my first todo");
    return todo;
  }

  // This method is called if application XML or JSON is request
  @GET
  @Produces({ MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
  public ToDoElement getXML() {
    ToDoElement todo = new ToDoElement();
    todo.setSummary("This is my first todo");
    todo.setDescription("This is my first todo");
    return todo;
  }
  
  @PUT
  @Consumes({ MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
  public Response putXML(ToDoElement obj){
	  System.out.println("put="+obj.toString());
	  return Response.ok().build();
  }
  
  @GET
  @Path("{name}")
  @Produces({ MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
  public ToDoElement getResource(@PathParam ( "name" ) String name) throws ResourceNotFoundException{	
    ToDoElement todo=null;
    
	todo = resources.get(name);
	if(todo==null){
		throw new ResourceNotFoundException(name);
	}
    return todo;
  }
  
  @POST
  @Consumes({ MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
  @Produces({MediaType.APPLICATION_JSON})
  public Response postCreate(ToDoElement obj,@Context UriInfo uriInfo){
	  
	  System.out.println("post="+obj.toString());
	  Response r=null;
	  ToDoResponse response=new ToDoResponse();	  
	  URI uri = null;
	  if(obj.getResource()!=null){
		//persisted into hash
		resources.put(obj.getResource(),obj);
		uri=uriInfo.getAbsolutePathBuilder().path(obj.getResource()).build();
		response.setStatus(201);
		response.setResource(uri.getPath().toString());
		response.setMessage("Object has been added");
		r=Response.created(uri).entity(response).build();
	  }
	  else {
		  response.setStatus(304);
		  response.setMessage("Resource is empty");
		  r=Response.ok().entity(response).build();
	  }
	  return r;
  }
  
  @DELETE
  @Path("{resource}")
  public Response delete(@PathParam ("resource") String resource) throws ResourceNotFoundException{
	  if(resource!=null && !resource.isEmpty()) {
		  ToDoElement rez=resources.remove(resource);
		  if(rez==null) {
			  throw new ResourceNotFoundException(resource + " does not exist");
		  }
	  }
	  return Response.ok().build();
  }

  /*
  @Deprecated
  @POST
  @Consumes({ MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
  @Produces({MediaType.APPLICATION_JSON})
  public Response postXML(Todo obj, @Context UriInfo uriInfo){
	  System.out.println("post="+obj.toString());
	  Response r=null;
	  ToDoResponse response=new ToDoResponse();

	  URI uri = null;
	  if(obj.getResource()!=null){
		  uri=uriInfo.getAbsolutePathBuilder().path(obj.getResource()).build();
		  response.setStatus(201);
		  
	  }
	  else {
		  response.setStatus(200);
	  }
	  response.setMessage("This is the response");	  
	  response.setResource(obj.getResource());	  
	  if(obj.getResource()!=null){
		  r=Response.created(uri).entity(response).build();
	  }
	  else {
		  r=Response.ok().entity(response).build();
	  }
	  System.out.println("response=" + r.toString());
	  return r;
  }
  */

  //-------------------------------------------------------
  // Injection of classes using plain text
  //-------------------------------------------------------
  @PUT
  @Consumes({MediaType.TEXT_PLAIN})
  public Response putClass(String serialized){
	  ObjectMapper mapper = new ObjectMapper();
	  ToDoElement bla=new ToDoElement();
	  try {
		bla=mapper.readValue(serialized,ToDoElement.class);
		System.out.println("put="+bla.toString());
	}catch (Exception e) {
		e.printStackTrace();
		System.out.println(e.getLocalizedMessage());
	}
	return Response.status(401).build();
  }

  @POST
  @Consumes({MediaType.TEXT_PLAIN})
  public void postClass(String serialized){
	  ObjectMapper mapper = new ObjectMapper();
	  ToDoElement bla=new ToDoElement();
	  try {
		bla=mapper.readValue(serialized,ToDoElement.class);
		System.out.println("post="+bla.toString());
	}catch (Exception e) {
		e.printStackTrace();
		System.out.println(e.getLocalizedMessage());
	}
  }
} 