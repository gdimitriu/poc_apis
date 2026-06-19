package gaby.jersey.jaxb.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class TodoResourceTestBase {

    private Client client;

    protected abstract String getBaseUri();

    @BeforeEach
    void setUpClient() {
        client = ClientBuilder.newClient().register(MultiPartFeature.class);
    }

    @AfterEach
    void tearDownClient() {
        client.close();
    }

    @Test
    void testGetTextXml() {
        Response response = client.target(getBaseUri()).path("todo")
                .request(MediaType.TEXT_XML)
                .get();
        assertEquals(200, response.getStatus());
        String body = response.readEntity(String.class);
        assertTrue(body.contains("This is my first todo"), "Expected todo content in text/xml response");
    }

    @Test
    void testGetApplicationXml() {
        Response response = client.target(getBaseUri()).path("todo")
                .request(MediaType.APPLICATION_XML)
                .get();
        assertEquals(200, response.getStatus());
        String body = response.readEntity(String.class);
        assertTrue(body.contains("This is my first todo"), "Expected todo content in application/xml response");
        assertTrue(body.contains("<todo>") || body.contains("<todo "), "Expected <todo> root element");
    }

    @Test
    void testGetApplicationJson() {
        Response response = client.target(getBaseUri()).path("todo")
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
        String body = response.readEntity(String.class);
        assertTrue(body.contains("This is my first todo"), "Expected todo content in JSON response");
        assertTrue(body.contains("summary"), "Expected 'summary' field in JSON response");
    }

    @Test
    void testPostMultipart() {
        FormDataMultiPart multipart = new FormDataMultiPart()
                .field("Name", "ala bala name", MediaType.TEXT_PLAIN_TYPE)
                .field("data", "ala bala data", MediaType.TEXT_PLAIN_TYPE);
        Response response = client.target(getBaseUri()).path("todo")
                .request()
                .post(Entity.entity(multipart, multipart.getMediaType()));
        assertEquals(204, response.getStatus());
    }
}
