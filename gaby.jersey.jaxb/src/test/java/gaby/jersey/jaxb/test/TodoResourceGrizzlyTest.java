package gaby.jersey.jaxb.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import gaby.jersey.jaxb.json.AppConfig;

public class TodoResourceGrizzlyTest extends TodoResourceTestBase {

    private static HttpServer server;
    private static int port;

    @BeforeAll
    static void startServer() throws IOException {
        port = findFreePort();
        server = GrizzlyHttpServerFactory.createHttpServer(
                URI.create("http://localhost:" + port + "/"),
                new AppConfig());
    }

    @AfterAll
    static void stopServer() {
        server.shutdownNow();
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
