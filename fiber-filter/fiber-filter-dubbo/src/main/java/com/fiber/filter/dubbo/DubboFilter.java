package com.fiber.filter.dubbo;

import com.fiber.common.annotation.RouteMethod;
import com.fiber.common.constants.Constants;
import com.fiber.common.enums.FiberFilters;
import com.fiber.common.model.FiberContext;
import com.fiber.common.model.RouteData;
import com.fiber.filter.api.FiberFilter;
import com.fiber.filter.dubbo.handler.DubboServiceHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

/**
 * @author panyox
 */
@Slf4j
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
        //do invoke dubbo
        if (RouteMethod.GET.equals(RouteMethod.valueOf(context.getMethod()))) {
            Object data = Optional.ofNullable(exchange.getAttribute(Constants.QUERY_DATA)).orElse("{}");
            log.info("query: {}", data);
            exchange.getAttributes().put(Constants.FIBER_CONTENT, data);
        } else if (RouteMethod.POST.equals(RouteMethod.valueOf(context.getMethod()))) {
            Object res = Optional.ofNullable(exchange.getAttribute(Constants.FORM_DATA)).orElse(exchange.getAttribute(Constants.BODY_DATA));
            exchange.getAttributes().put(Constants.FIBER_CONTENT, res);
        }
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
