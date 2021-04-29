package com.fiber.filter.router;

import com.fiber.common.cache.RouteCache;
import com.fiber.common.constants.Constants;
import com.fiber.common.enums.FiberFilters;
import com.fiber.common.enums.ResponseCode;
import com.fiber.common.exception.FiberException;
import com.fiber.common.model.FiberContext;
import com.fiber.common.model.RouteData;
import com.fiber.common.model.ServiceData;
import com.fiber.common.utils.RouteUtil;
import com.fiber.filter.api.FiberFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author panyox
 */
@Slf4j
public class RouteFilter implements FiberFilter {

    @Override
    public Mono<FiberFilter> filter(ServerWebExchange exchange) {
        FiberContext context = exchange.getAttribute(Constants.FIBER_CONTEXT);
        Objects.requireNonNull(context);
        RouteData routeData = matchRoute(context);
        if (Objects.isNull(routeData)) {
            routeNotFound(context);
        }
        log.info("route data: {}", routeData);
        if (!routeData.getEnable()) {
            routeNotable(context);
        }
        exchange.getAttributes().put(Constants.ROUTE_DATA, routeData);
        ServiceData serviceData = matchService(routeData.getServiceId());
        if (Objects.isNull(serviceData)) {
            serviceNotFound(context);
        }
        if (!serviceData.getEnable()) {
            serviceNotable(context);
        }
        exchange.getAttributes().put(Constants.SERVICE_DATA, serviceData);
        return Mono.just(this);
    }

    private RouteData matchRoute(FiberContext context) {
        String routeId = RouteUtil.generateRouteId(context);
        return RouteCache.getInstance().getRoute(routeId);
    }

    private ServiceData matchService(String id) {
        return RouteCache.getInstance().getService(id);
    }

    private void routeNotFound(FiberContext context) {
        log.info("path:{} method: {} not match, check out configuration", context.getPath(), context.getMethod());
        throw new FiberException(ResponseCode.ROUTE_NOT_FOUND);
    }

    private void routeNotable(FiberContext context) {
        log.info("path:{} method: {} is not able", context.getPath(), context.getMethod());
        throw new FiberException(ResponseCode.ROUTE_NOT_ABLE);
    }

    private void serviceNotFound(FiberContext context) {
        log.info("path:{} method: {} service not found, check out configuration", context.getPath(), context.getMethod());
        throw new FiberException(ResponseCode.SERVICE_NOT_FOUND);
    }

    private void serviceNotable(FiberContext context) {
        log.info("path:{} method: {} service is not able", context.getPath(), context.getMethod());
        throw new FiberException(ResponseCode.SERVICE_NOT_ABLE);
    }

    @Override
    public int index() {
        return FiberFilters.ROUTER.getIndex();
    }

    @Override
    public String name() {
        return FiberFilters.ROUTER.getName();
    }
}
