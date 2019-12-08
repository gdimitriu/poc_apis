package gaby.jersey.jaxb.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import com.wordnik.swagger.jaxrs.config.BeanConfig;


public class ServerJetty {

	public static void main(String[] args) {
		try {
			

	        BeanConfig beanConfig = new BeanConfig();
	        beanConfig.setVersion("0.0.1");
	        beanConfig.setResourcePackage("gaby.jersey.jaxb.resources"); // replace with your packages
	        beanConfig.setBasePath("http://localhost:8090");
	        beanConfig.setDescription("My RESTful resources");
	        beanConfig.setTitle("My RESTful API");
	        beanConfig.setScan(true);
	        
			ServletHolder sh = new ServletHolder(ServletContainer.class);
	        sh.setInitParameter("jersey.config.server.provider.packages","gaby.jersey.jaxb");
	        sh.setInitParameter("javax.ws.rs.Application","gaby.jersey.jaxb.json.AppConfig");
	        sh.setInitParameter("com.sun.jersey.config.property.packages", "rest");//Set the package where the services reside
	        sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "false");


	        final Server server = new Server(8090);	       
	         
	        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
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