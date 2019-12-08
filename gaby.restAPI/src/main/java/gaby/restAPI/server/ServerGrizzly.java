package gaby.restAPI.server;

import gaby.restAPI.json.AppConfig;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

public class ServerGrizzly {

	private static final URI BASE_URI=URI.create("http://localhost:8080/");
	public static void main(String[] args) {
		try {
			final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI,new AppConfig(),false);
			server.start();
			System.out.println(String.format("Application started.%nHit enter to stop it..."));
			System.in.read();
			server.shutdownNow();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
	}
}
/*
com.wordnik.swagger.jaxrs.listing.ApiListingResource.class,        
com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class,
com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class,
com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class,
*/