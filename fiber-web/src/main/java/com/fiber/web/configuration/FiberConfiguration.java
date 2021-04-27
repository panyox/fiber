package com.fiber.web.configuration;

import com.fiber.filter.api.FiberFilter;
import com.fiber.web.handler.DefaultFiberHandler;
import com.fiber.web.handler.FiberHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author panyox
 */
@Configuration
public class FiberConfiguration {

    /**
     * fiber handler
     *
     * @param filters
     * @return
     */
    @Bean
    public FiberHandler fiberHandler(final ObjectProvider<List<FiberFilter>> filters) {
        List<FiberFilter> fiberFilters = filters.getIfAvailable(Collections::emptyList);
        List<FiberFilter> filterList = fiberFilters.stream().sorted(Comparator.comparingInt(FiberFilter::index)).collect(Collectors.toList());
        return new DefaultFiberHandler(filterList);
    }

    /**
     * proxy and handler request
     *
     * @param handler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> route(FiberHandler handler) {
        return RouterFunctions.route(RequestPredicates.all(), handler::handle);
    }
}
