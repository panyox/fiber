package com.fiber.demo.bootstrap.configuration;

import com.fiber.common.response.ResponseBuilder;
import com.fiber.demo.bootstrap.result.CustomResultBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author panyox
 */
@Configuration
public class BootstrapConfiguration {

    @Bean
    public ResponseBuilder<?> responseBuilder() {
        return new CustomResultBuilder();
    }
}
