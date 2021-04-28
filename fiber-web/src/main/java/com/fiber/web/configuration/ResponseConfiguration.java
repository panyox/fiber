package com.fiber.web.configuration;

import com.fiber.common.response.ResponseBuilder;
import com.fiber.web.response.DefaultResponseBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.context.annotation.Bean;

/**
 * @author panyox
 */
public class ResponseConfiguration {

    @Bean
    @ConditionalOnMissingBean(value = ResponseBuilder.class, search = SearchStrategy.ALL)
    public ResponseBuilder<?> responseBuilder() {
        return new DefaultResponseBuilder();
    }
}
