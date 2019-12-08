package gaby.restAPI.client;

import gaby.restAPI.model.ToDoElement;
import gaby.restAPI.model.ToDoList;
import gaby.restAPI.model.ToDoResponse;
import gaby.restAPI.model.ToDoUser;
import gaby.restAPI.model.ToDoUserResponse;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Test {
  public static void main(String[] args) {
	Client client = ClientBuilder.newClient();
	WebTarget target = client.target(getBaseURI());
	
	//------------------------------------------------------------------------------------------------------------
	System.out.println("------------------------------------------------------------------------------------------------------------");
	System.out.println("Authenticate");
	Map<String, NewCookie> cookieAuth=null; 
	//create the json from class
	ToDoUser user=new ToDoUser();
	user.setName("admin");
	user.setUser("admin");
	user.setPassword("admin");
	ObjectWriter owUser = new ObjectMapper().writer().withDefaultPrettyPrinter();
	String jsonUser=null;
	try {
		jsonUser = owUser.writeValueAsString(user);
	} catch (JsonProcessingException e1) {
		e1.printStackTrace();
		return;
	}
	Response authResp=target.path("rest/user").request(MediaType.APPLICATION_JSON).post(Entity.entity(jsonUser, MediaType.APPLICATION_JSON));
	if(authResp.getStatus()!=200) {
		System.out.println("Authentication failed " + authResp.toString());
		return;
	}
	if(authResp.hasEntity()){
    	ObjectReader orUser=new ObjectMapper().reader(ToDoUserResponse.class);
    	ToDoUserResponse userResponse=new ToDoUserResponse();
    	try {
			userResponse=orUser.readValue(authResp.readEntity(String.class));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("authentication failed " + authResp.toString());
			return;
		}
    	System.out.println("created the resource " + userResponse.toString());
	}
	cookieAuth=authResp.getCookies();
	//------------------------------------------------------------------------------------------------------------
	System.out.println("------------------------------------------------------------------------------------------------------------");
	
	// Get JSON for application
	// System.out.println(target.path("rest").path("api-docs").request(MediaType.APPLICATION_JSON).get().toString());
	
	//------------------------------------------------------------------------------------------------------------
	System.out.println("------------------------------------------------------------------------------------------------------------");
    // Get XML
    System.out.println(target.path("rest").path("todo").queryParam("user", "admin").queryParam("password", "admin").request(MediaType.TEXT_XML).get(String.class));
    // Get XML for application
    System.out.println(target.path("rest").path("todo").request(MediaType.APPLICATION_XML).get(String.class));
    // Get JSON for application
    System.out.println(target.path("rest").path("todo").request(MediaType.APPLICATION_JSON).get().toString());
    
    //post using JSON already serialized return string or json serialized
    String str="{\"summary\":\"Summary of nothing\",\"description\":\"Description of nothing\"}";
    
    String ret=target.path("rest").path("todo").request(MediaType.APPLICATION_JSON).post(Entity.entity(str, MediaType.APPLICATION_JSON),String.class);
    System.out.println("POST return string=" + ret);
    ret=target.path("rest").path("todo").request(MediaType.APPLICATION_JSON).put(Entity.entity(str, MediaType.APPLICATION_JSON),String.class);
    System.out.println("PUT return string=" + ret);

    //------------------------------------------------------------------------------------------------------------
    System.out.println("------------------------------------------------------------------------------------------------------------");
    System.out.println("get the json of class and response");
    
    try {
		Response data=target.path("rest").path("todo").request(MediaType.APPLICATION_JSON).get();
		System.out.println("get the class="+data.toString());
		if(data.hasEntity()){
			System.out.println(data.readEntity(String.class));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
    
    try {
		Response resp =target.path("rest").path("todo").request(MediaType.APPLICATION_JSON).put(Entity.entity(str, MediaType.APPLICATION_JSON));
		System.out.println("rez of put=" + resp.toString());
		if(resp.hasEntity()){
			System.out.println(resp.readEntity(String.class));
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
    
    //------------------------------------------------------------------------------------------------------------
    System.out.println("------------------------------------------------------------------------------------------------------------");
    System.out.println("Using class injection with plain element");

    ObjectMapper mapper = new ObjectMapper();
    try {
    	ToDoElement create=new ToDoElement();
    	create.setDescription("new element");
    	create.setSummary("New element created");
    	create.setResource("new");
    	int status = target.path("rest").path("todo").request(MediaType.TEXT_PLAIN).
			post(Entity.entity(mapper.writeValueAsString(create), MediaType.TEXT_PLAIN)).getStatus();
    	System.out.println("post : status is " + status);
	} catch (Exception e) {
		e.printStackTrace();
	}    	
    try {
    	ToDoElement bla=new ToDoElement();
        bla.setDescription("Description of nothing todo");
        bla.setSummary("Summary description of nothign todo");		
    	int status=target.path("rest").path("todo").request(MediaType.TEXT_PLAIN).
			put(Entity.entity(mapper.writeValueAsString(bla), MediaType.TEXT_PLAIN)).getStatus();
    	System.out.println("put : status is " + status);
	} catch (Exception e) {
		e.printStackTrace();
	}    

    //------------------------------------------------------------------------------------------------------------
    System.out.println("------------------------------------------------------------------------------------------------------------");
    System.out.println("Using class injection on post and get the new resources using get");

    try {
    	ToDoElement create=new ToDoElement();
    	Response rez=null;
    	
    	create.setDescription("new element");
    	create.setSummary("New element created");
    	create.setResource("new");
    	
    	//create the json from class
    	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    	String json = ow.writeValueAsString(create);
    	
    	//create a resource using post
    	rez=target.path("rest").path("todo").request(MediaType.APPLICATION_JSON).
			post(Entity.entity(json, MediaType.APPLICATION_JSON));
    	int status=rez.getStatus();
    	System.out.println("post : status is " + status);
    	if(rez.hasEntity()){
        	ObjectReader or=new ObjectMapper().reader(ToDoResponse.class);
        	ToDoResponse received=new ToDoResponse();
        	received=or.readValue(rez.readEntity(String.class));
        	System.out.println("created the resource " + received.toString());
        	
        	//get the created resource
        	rez=target.path(received.getResource()).request(MediaType.APPLICATION_JSON).get();
        	if(rez.getStatus()!=200) {
        		System.out.println("Exception cought in get " + rez.toString());
        	} else {
        		System.out.println("get the resource="+rez.toString());
        		if(rez.hasEntity()){
        			System.out.println(rez.readEntity(String.class));
        		}
        	}

        	//delete the created resource
        	rez=target.path(received.getResource()).request(MediaType.APPLICATION_JSON).delete();
        	if(rez.getStatus()!=200) {
        		if(rez.hasEntity()){
        			System.out.println("exception cought in delete " + rez.readEntity(String.class));
            	} else {
            		System.out.println("exception cought in delete " + rez.toString());
        		}
        	} else {
        		System.out.println("delete the resource="+rez.toString());
        		if(rez.hasEntity()){
        			System.out.println(rez.readEntity(String.class));
        		}
        	}

        	//delete again the created resource
        	rez=target.path(received.getResource()).request(MediaType.APPLICATION_JSON).delete();
        	if(rez.getStatus()!=200) {
        		if(rez.hasEntity()){
        			System.out.println("exception cought in delete " + rez.readEntity(String.class));
            	} else {
            		System.out.println("exception cought in delete " + rez.toString());
        		}
        	} else {
        		System.out.println("delete the resource="+rez.toString());
        		if(rez.hasEntity()){
        			System.out.println(rez.readEntity(String.class));
        		}
        	}
        	
        	//get already deleted resource
        	rez=target.path(received.getResource()).request(MediaType.APPLICATION_JSON).get();
        	if(rez.getStatus()!=200) {
        		if(rez.hasEntity()){
        			System.out.println("Exception cought in get " + rez.readEntity(String.class));
            	} else {
        			System.out.println("Exception cought in get " + rez.toString());
        		}
        	}
        	else {
        		System.out.println("get the resource="+rez.toString());
            	if(rez.hasEntity()){
            		System.out.println(rez.readEntity(String.class));
            	}
        	}
    	}
	} catch (Exception e) {
		System.out.println(e.getLocalizedMessage());
	}
    
    //------------------------------------------------------------------------------------------------------------
    System.out.println("------------------------------------------------------------------------------------------------------------");
    System.out.println("Using a new class a a wrapper over a list of elements");
    Response rez=target.path("rest/todo/elements").request(MediaType.APPLICATION_JSON).get();
    if(rez.hasEntity()){
    	ObjectReader or=new ObjectMapper().reader(ToDoElement.class);
    	ToDoElement received=new ToDoElement();
    	try {
			received=or.readValue(rez.readEntity(String.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	System.out.println("get value " + received.toString());
    }
    
    //get a list of elements using cookie
    rez=target.path("rest/todo/elements/list")
    		.request(MediaType.APPLICATION_JSON)
    		.cookie(cookieAuth.get("userId"))
    		.get();
    System.out.println("result of request : " + rez.toString());
    if(rez.hasEntity()){
    	ObjectReader or=new ObjectMapper().reader(ToDoList.class);
    	ToDoList received=new ToDoList();
    	try {
			received=or.readValue(rez.readEntity(String.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	System.out.println("get value " + received.toString());

    }
    
    //get a list of elements using query parameters
    rez=target.path("rest/todo/elements/list")
    		.queryParam("user", "admin").queryParam("password","admin")
    		.request(MediaType.APPLICATION_JSON)
    		.get();
    System.out.println("result of request : " + rez.toString());
    if(rez.hasEntity()){
    	ObjectReader or=new ObjectMapper().reader(ToDoList.class);
    	ToDoList received=new ToDoList();
    	try {
			received=or.readValue(rez.readEntity(String.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	System.out.println("get value " + received.toString());

    }
}

  private static URI getBaseURI() {
    return UriBuilder.fromUri("{arg}").build(new String[]{"http://localhost:8080/"},false);
  }

} 