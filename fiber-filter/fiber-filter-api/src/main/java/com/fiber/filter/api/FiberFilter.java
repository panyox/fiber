package com.fiber.filter.api;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author panyox
 */
public interface FiberFilter {

    /**
     * filter
     *
     * @return
     */
    Mono<FiberFilter> filter(ServerWebExchange exchange);

    /**
     * filter index
     *
     * @return
     */
    default int index() {
        return 99;
    }

    /**
     * filter name
     *
     * @return
     */
    default String name() {
        return "";
    }
}
