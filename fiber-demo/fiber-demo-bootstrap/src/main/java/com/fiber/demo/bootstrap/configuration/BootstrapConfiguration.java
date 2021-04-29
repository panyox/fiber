package com.fiber.demo.bootstrap.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author panyox
 */
@Configuration
public class BootstrapConfiguration {

    /**
     * custom result
     *
     * @return
     */
//    @Bean
//    public ResponseBuilder<?> responseBuilder() {
//        return new CustomResultBuilder();
//    }
    @Bean
    public TestData testData() {
        TestData td = new TestData();
        td.initRoute();
        return td;
    }
}
