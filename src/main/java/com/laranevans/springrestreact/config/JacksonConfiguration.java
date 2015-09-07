package com.laranevans.springrestreact.config;

import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// This configuration is needed in order to properly convert to/from java.time objects.
@Configuration
public class JacksonConfiguration {

    @Bean
    public JSR310Module jsr310Module() {
        return new JSR310Module();
    }

}