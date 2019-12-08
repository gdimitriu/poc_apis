package gaby.jersey.jaxb.json;

import gaby.jersey.jaxb.resources.TodoResource;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class AppConfig extends ResourceConfig {
	public AppConfig() {
		super(MyJSONFeatures.class,
				TodoResource.class,
				MultiPartFeature.class);
	}
}
