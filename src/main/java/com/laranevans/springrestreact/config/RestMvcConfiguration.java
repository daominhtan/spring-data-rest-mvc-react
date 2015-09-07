package com.laranevans.springrestreact.config;

import com.laranevans.springrestreact.model.domain.Possession;
import com.laranevans.springrestreact.model.domain.Person;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
public class RestMvcConfiguration extends RepositoryRestMvcConfiguration {

    @Override
    public RepositoryRestConfiguration config() {
        RepositoryRestConfiguration config = super.config();
        /*
         Setting the baseUri effectively namespaces your REST endpoints, making them easier to secure. You can just
         secure "/api/**" and you're good.
         */
        config.setBaseUri("/api");
        /*
         Without this along with the exposeIdsFor call in configureRepositoryRestConfiguration below, id values aren't
         returned when new domain entities are created with POST requests. Having the id in the response is valuable
         because without it you need to send an additional GET request to retrieve the id. Having it in there saves a
         round trip when creating things.
         */
        config.setReturnBodyOnCreate(true);
        /*
         Returning the body on update is nice because updates can cause things like instance version and updated_at
         values can be changed when updating other attributes of an object. Returning the updated object in the
         response body makes those updates immediately available to you, again, saving you a round trip.
         */
        config.setReturnBodyOnUpdate(true);
        return config;
    }

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        /*
         Note the refences to the specific sample domain model classes.
         You should/would change the arguments to the classes in your domain model.

         Having the IDs exposed makes it possible to use the IDs in javascript. You COULD refer to the URI of the
         instance as an identifier. But, to me it's just easier to keep the API on the client similar to the API on
         the server.
         */
        config.exposeIdsFor(Person.class, Possession.class);
    }

}