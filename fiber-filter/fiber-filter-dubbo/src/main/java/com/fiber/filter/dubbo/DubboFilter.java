package com.fiber.filter.dubbo;

import com.fiber.common.constants.Constants;
import com.fiber.common.enums.FiberFilters;
import com.fiber.common.enums.RouteMethod;
import com.fiber.common.model.FiberContext;
import com.fiber.common.model.RouteData;
import com.fiber.filter.api.AsyncFiberFilter;
import com.fiber.filter.api.FiberFilter;
import com.fiber.filter.dubbo.handler.DubboServiceHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author panyox
 */
@Slf4j
public class DubboFilter extends AsyncFiberFilter {

    private DubboServiceHandler serviceHandler;

    public DubboFilter(DubboServiceHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
    }

    @Override
    public Mono<FiberFilter> async(CompletableFuture<Object> future, ServerWebExchange exchange) {
        FiberContext context = exchange.getAttribute(Constants.FIBER_CONTEXT);
        Objects.requireNonNull(context);
        RouteData routeData = exchange.getAttribute(Constants.ROUTE_DATA);
        Objects.requireNonNull(routeData);
        //do invoke dubbo
        Object parameters;
        if (RouteMethod.GET.equals(RouteMethod.valueOf(context.getMethod()))) {
            parameters = Optional.ofNullable(exchange.getAttribute(Constants.QUERY_DATA)).orElse("{}");
        } else {
            parameters = Optional.ofNullable(exchange.getAttribute(Constants.FORM_DATA)).orElse(exchange.getAttribute(Constants.BODY_DATA));
        }
        CompletableFuture<Object> result = serviceHandler.invokeAsync((String) parameters, routeData, exchange);
        result.thenApply(res -> {
            future.complete(res);
            return res;
        });
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
