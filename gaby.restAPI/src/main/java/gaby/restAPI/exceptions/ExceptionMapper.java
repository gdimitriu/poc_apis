package gaby.restAPI.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Throwable> {

	public Response toResponse(Throwable throwable) {
		if ( throwable instanceof ResourceNotFoundException ) {
            return Response.status( Status.NOT_FOUND ).entity( throwable.getMessage() ).type( "text/plain" ).build();
        }
		return Response.status( Status.INTERNAL_SERVER_ERROR ).entity( throwable.getMessage() ).type( "text/plain" ).build();
	}

}
