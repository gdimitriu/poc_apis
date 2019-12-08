package gaby.jersey.jaxb.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.internal.MultiPartWriter;


public class Test {
  public static void main(String[] args) {
	Client client = ClientBuilder.newClient().register(MultiPartFeature.class).register(MultiPartWriter.class);
	WebTarget target = client.target(getBaseURI());
	FormDataMultiPart multipartEntity = new FormDataMultiPart()
    .field("Name","ala bala name", MediaType.TEXT_PLAIN_TYPE)
    .field("data","ala bala data", MediaType.TEXT_PLAIN_TYPE);

	target.path("todo").request(new String[]{MediaType.MULTIPART_FORM_DATA}).post(Entity.entity(multipartEntity, multipartEntity.getMediaType()));
    // Get XML
    System.out.println(target.path("todo").request(new String[]{MediaType.TEXT_XML}).get(String.class));
    // Get XML for application
    System.out.println(target.path("todo").request(new String[]{MediaType.APPLICATION_XML}).get(String.class));
    // Get JSON for application
    System.out.println(target.path("todo").request(new String[]{MediaType.APPLICATION_JSON}).get(String.class));
  }

  private static URI getBaseURI() {
    return UriBuilder.fromUri("{arg}").build(new String[]{"http://localhost:8090/"},false);
  }

} 