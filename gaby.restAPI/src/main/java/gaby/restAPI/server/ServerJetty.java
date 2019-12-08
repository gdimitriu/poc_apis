package gaby.restAPI.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import com.wordnik.swagger.jaxrs.config.BeanConfig;


public class ServerJetty {

	public static void main(String[] args) {
		try {
			

//	        BeanConfig beanConfig = new BeanConfig();
//	        beanConfig.setVersion("0.0.1");
//	        beanConfig.setResourcePackage("gaby.restAPI.resources"); // replace with your packages
//	        beanConfig.setBasePath("http://localhost:8080");
//	        beanConfig.setDescription("My RESTful resources");
//	        beanConfig.setTitle("My RESTful API");
//	        beanConfig.setScan(true);
	        
			ServletHolder sh = new ServletHolder(ServletContainer.class);
	        sh.setInitParameter("jersey.config.server.provider.packages","gaby.restAPI");
	        sh.setInitParameter("javax.ws.rs.Application","gaby.restAPI.json.AppConfig");
	        sh.setInitParameter("com.sun.jersey.config.property.packages", "rest");//Set the package where the services reside
	        sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "false");


	        final Server server = new Server(8080);	       
	         
	        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
	        //This is the Integrator REST-API
	        context.addServlet(sh, "/*");
			
			server.start();
//			server.join();
			System.out.println(String.format("Application started.%nHit enter to stop it..."));
			System.in.read();
			server.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
	}
}