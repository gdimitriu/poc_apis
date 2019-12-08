package gaby.restAPI.resources;

import gaby.restAPI.model.ToDoElement;
import gaby.restAPI.model.ToDoList;
import gaby.restAPI.model.ToDoUserResponse;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.annotations.ApiResponse;

import org.apache.commons.codec.binary.Base64;

@Path("/rest/todo/elements")
@Api(value="/rest/todo/elements",description="Element resources")
public class ToDoElementResources {

	@Context
	UriInfo uriInfo;	

	@GET
	@ApiOperation(value="Get",notes="just get a message")
	@Produces({MediaType.APPLICATION_JSON})
	public ToDoElement getJSON() {
		ToDoElement todo = new ToDoElement();
		todo.setSummary("todo/elements");
		todo.setDescription("todo/elements description");
		todo.setResource("/rest/todo/elements");
		return todo;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/list")	
	@ApiOperation(value="Get list",notes="get the list of the elements")
	@ApiResponses(value = {
			@ApiResponse(code=401, message="authenticate failed"),
			@ApiResponse(code=200,message="OK")}) 
	public Response listResources(@Context HttpHeaders hh,
			@QueryParam("user") final String user,
			@QueryParam("password") final String password
	) throws JsonProcessingException, Throwable {
		
	    Map<String, Cookie> pathParams = hh.getCookies();
	    ToDoUserResponse received=null;
	    Cookie cookie=pathParams.get("userId");
	    if(cookie!=null){
		    ObjectReader or=new ObjectMapper().reader(ToDoUserResponse.class);
	    	received=new ToDoUserResponse();
	    	String str=new String(Base64.decodeBase64(cookie.getValue().getBytes()));
	    	
	    	received=or.readValue(str);	    	
	    }
    	
	    if(received!=null){
	    	if(!received.getToken().equals("1234-1234") || !received.getUserid().equals("qwerty")) {
	    		System.out.println("authenticate failed on token");
	    		return Response.status(401).build();
	    	}
	    }
	    else {
	    	if (!(user!=null && password!=null)) {
	    		System.out.println("authenticate failed no user");
	    		return Response.status(401).build();
	    	}
	    	if(!(user.equals("admin") && password.equals("admin") )) {
	    		System.out.println("authenticate failed wrong passwd");
	    		return Response.status(401).build();
	    	}
	    }
	    
		List<String> resources=new ArrayList<String>();
		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI newUri = ub.path("A1").build();
		resources.add(newUri.toASCIIString());
		ub = uriInfo.getAbsolutePathBuilder();
		newUri = ub.path("A2").build();
		resources.add(newUri.toASCIIString());
		ub = uriInfo.getAbsolutePathBuilder();
		newUri = ub.path("A3").build();
		resources.add(newUri.toASCIIString());
		ToDoList list=new ToDoList();
		list.setResources(resources);
		return Response.status(200).entity(list).build();
	}
}
