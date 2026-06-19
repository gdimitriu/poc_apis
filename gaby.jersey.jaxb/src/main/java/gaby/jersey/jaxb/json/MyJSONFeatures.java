package gaby.jersey.jaxb.json;

import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.MessageBodyWriter;

import org.glassfish.jersey.CommonProperties;

public class MyJSONFeatures implements Feature {

	@Override
	public boolean configure(final FeatureContext context) {
		final String disableMoxy = CommonProperties.MOXY_JSON_FEATURE_DISABLE + '.'

                + context.getConfiguration().getRuntimeType().name().toLowerCase();

        context.property(disableMoxy, true);

        context.register(JacksonWithJoda.class, MessageBodyReader.class, MessageBodyWriter.class);

        return true;

	}

}
