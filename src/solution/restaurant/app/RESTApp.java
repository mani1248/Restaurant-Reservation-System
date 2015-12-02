package solution.restaurant.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.jaxrs.config.BeanConfig;


@ApplicationPath("/api")
public class RESTApp extends ResourceConfig{
	
	public RESTApp(){
		
		packages("solution.restaurant.rest"); 
		
		//Swagger code
		
		register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

		
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath("ReservationSystem/api");
        beanConfig.setResourcePackage("solution.restaurant.rest.controllers");
        beanConfig.setTitle("REST documentation");
        beanConfig.setDescription("REST api documentation for app");
        beanConfig.setScan(true);
	}
}
