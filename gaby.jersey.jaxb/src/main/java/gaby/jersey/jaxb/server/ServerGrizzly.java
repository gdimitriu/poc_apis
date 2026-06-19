package gaby.jersey.jaxb.server;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import gaby.jersey.jaxb.json.AppConfig;


public class ServerGrizzly {

	public static void main(String[] args) {
		try {
			final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
					URI.create("http://localhost:8090/"),
					new AppConfig());

			System.out.println(String.format("Application started.%nHit enter to stop it..."));
			System.in.read();
			server.shutdownNow();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
