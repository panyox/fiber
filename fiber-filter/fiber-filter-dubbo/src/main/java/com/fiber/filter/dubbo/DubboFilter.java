package com.fiber.filter.dubbo;

import com.fiber.common.constants.Constants;
import com.fiber.common.enums.FiberFilters;
import com.fiber.common.model.FiberContext;
import com.fiber.common.model.RouteData;
import com.fiber.filter.api.FiberFilter;
import com.fiber.filter.dubbo.handler.DubboServiceHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author panyox
 */
public class DubboFilter implements FiberFilter {

    private DubboServiceHandler serviceHandler;

    public DubboFilter(DubboServiceHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
    }

    @Override
    public Mono<FiberFilter> filter(ServerWebExchange exchange) {
        FiberContext context = exchange.getAttribute(Constants.FIBER_CONTEXT);
        Objects.requireNonNull(context);
        RouteData routeData = exchange.getAttribute(Constants.ROUTE_DATA);
        Objects.requireNonNull(routeData);

        
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
