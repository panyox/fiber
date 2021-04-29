package com.fiber.filter.data;

import com.fiber.common.enums.FiberFilters;
import com.fiber.filter.api.FiberFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author panyox
 */
public class DataFilter implements FiberFilter {

    @Override
    public Mono<FiberFilter> filter(ServerWebExchange exchange) {
        return Mono.just(this);
    }

    @Override
    public int index() {
        return FiberFilters.DATA.getIndex();
    }

    @Override
    public String name() {
        return FiberFilters.DATA.getName();
    }
}
