package com.fiber.filter.dubbo;

import com.fiber.common.enums.FiberFilters;
import com.fiber.filter.api.FiberFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author panyox
 */
public class DubboFilter implements FiberFilter {

    @Override
    public Mono<FiberFilter> filter(ServerWebExchange exchange) {
        
        return Mono.just(this);
    }

    @Override
    public String name() {
        return FiberFilters.DUBBO.getName();
    }

    @Override
    public int index() {
        return FiberFilters.DUBBO.getIndex();
    }
}
