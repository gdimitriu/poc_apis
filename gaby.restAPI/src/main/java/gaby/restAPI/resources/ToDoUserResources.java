package gaby.restAPI.resources;

import gaby.restAPI.model.ToDoUser;
import gaby.restAPI.model.ToDoUserResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import org.apache.commons.codec.binary.Base64;

@Path("/rest/user")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Api(value="/rest/user",description="User management resources")
public class ToDoUserResources {

	@POST
	@ApiOperation(value="post user",notes="return the tonken and athenticate")
	public Response AuthenticateUser(ToDoUser userJSON) throws JsonProcessingException {
		//authenticate in passport.
		Response r=null;		
		ToDoUserResponse response=new ToDoUserResponse("qwerty","1234-1234");
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    	String json = ow.writeValueAsString(response);
    	byte[] base64json=Base64.encodeBase64(json.getBytes());
    	NewCookie cookie=new NewCookie("userId",new String(base64json));
    	System.out.println(cookie.toString());		
		r=Response.ok(response).cookie(cookie).build();
		return r;
	}
}
