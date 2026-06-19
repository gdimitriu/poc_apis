package gaby.jersey.jaxb.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;


public class ServerJetty {

	public static void main(String[] args) {
		try {
			ServletHolder sh = new ServletHolder(ServletContainer.class);
	        sh.setInitParameter("jersey.config.server.provider.packages", "gaby.jersey.jaxb");
	        sh.setInitParameter("jakarta.ws.rs.Application", "gaby.jersey.jaxb.json.AppConfig");

	        final Server server = new Server(8090);

	        ServletContextHandler context = new ServletContextHandler("/", ServletContextHandler.SESSIONS);
	        context.addServlet(sh, "/*");
	        server.setHandler(context);

			server.start();
			System.out.println(String.format("Application started.%nHit enter to stop it..."));
			System.in.read();
			server.stop();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
