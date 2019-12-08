package gaby.restAPI.json;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.glassfish.jersey.CommonProperties;

public class MyJSONFeatures implements Feature {

	public boolean configure(final FeatureContext context) {
		final String disableMoxy = CommonProperties.MOXY_JSON_FEATURE_DISABLE + '.'

                + context.getConfiguration().getRuntimeType().name().toLowerCase();

        context.property(disableMoxy, true);

        context.register(JacksonWithJoda.class, MessageBodyReader.class, MessageBodyWriter.class);

        return true;

	}

}
