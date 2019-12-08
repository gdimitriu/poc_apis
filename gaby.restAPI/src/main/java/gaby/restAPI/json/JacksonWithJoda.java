package gaby.restAPI.json;

import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Provider
public class JacksonWithJoda extends JacksonJsonProvider {
	
	public JacksonWithJoda() {

        ObjectMapper mapper = new ObjectMapper().registerModule(new JodaModule());

        mapper = mapper.setSerializationInclusion(Include.NON_NULL);

        setMapper(mapper);

	}

}
