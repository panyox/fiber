package com.fiber.filter.dubbo.handler;

import com.fiber.common.model.RouteData;
import com.fiber.filter.api.param.DubboParamBuilder;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author panyox
 */
public class DubboServiceHandler {

    private DubboParamBuilder paramBuilder;

    public DubboServiceHandler(DubboParamBuilder paramBuilder) {
        this.paramBuilder = paramBuilder;
    }

    Mono<Object> invoke(String parameters, RouteData routeData, ServerWebExchange exchange) {

        Pair<String[], Object[]> pair = paramBuilder.build(parameters, routeData.getParameters());
        
        return null;
    }
}
