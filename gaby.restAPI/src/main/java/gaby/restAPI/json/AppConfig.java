package gaby.restAPI.json;

import gaby.restAPI.exceptions.ExceptionMapper;
import gaby.restAPI.resources.ToDoElementResources;
import gaby.restAPI.resources.ToDoResources;
import gaby.restAPI.resources.ToDoUserResources;

import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;

@javax.ws.rs.ApplicationPath("/rest")
public class AppConfig extends ResourceConfig {
	public AppConfig() {
		super(	MyJSONFeatures.class,
				ExceptionMapper.class,
				EntityFilteringFeature.class,
				com.wordnik.swagger.jaxrs.listing.ApiListingResource.class,        
				com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class,
				com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class,
				com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class,				
				ToDoElementResources.class,
				ToDoResources.class,
				ToDoUserResources.class	
			);
	}
}

/*.register(new AbstractBinder() {
@Override
protected void configure() {
    bindFactory( testeDiverse.class).to( Object.class );
}
});
.register(RolesAllowedDynamicFeature.class,
	SelectableEntityFilteringFeature.class);
*/