package gaby.jersey.jaxb.test;

import java.io.IOException;
import java.net.ServerSocket;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import gaby.jersey.jaxb.json.AppConfig;

public class TodoResourceJettyTest extends TodoResourceTestBase {

    private static Server server;
    private static int port;

    @BeforeAll
    static void startServer() throws Exception {
        port = findFreePort();
        ServletHolder sh = new ServletHolder(new ServletContainer(new AppConfig()));

        server = new Server(port);
        ServletContextHandler context = new ServletContextHandler("/", ServletContextHandler.SESSIONS);
        context.addServlet(sh, "/*");
        server.setHandler(context);
        server.start();
    }

    @AfterAll
    static void stopServer() throws Exception {
        server.stop();
    }

    @Override
    protected String getBaseUri() {
        return "http://localhost:" + port + "/";
    }

    private static int findFreePort() throws IOException {
        try (ServerSocket s = new ServerSocket(0)) {
            return s.getLocalPort();
        }
    }
}
