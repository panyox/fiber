package com.fiber.filter.context;

import com.fiber.common.constants.Constants;
import com.fiber.common.enums.FiberFilters;
import com.fiber.common.enums.RpcType;
import com.fiber.common.model.FiberContext;
import com.fiber.filter.api.FiberFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.SystemProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Optional;

/**
 * @author panyox
 */
@Slf4j
public class ContextFilter implements FiberFilter {

    @Override
    public Mono<FiberFilter> filter(ServerWebExchange exchange) {
        final ServerHttpRequest request = exchange.getRequest();
        final HttpHeaders headers = request.getHeaders();
        String authName = Optional.ofNullable(SystemProperties.get("fiber.auth.name")).orElse("authorization");
        String rpcType = Optional.ofNullable(headers.getFirst(Constants.RPC_TYPE)).orElse(RpcType.DUBBO.getName());
        FiberContext context = new FiberContext();
        context.setStartTime(System.currentTimeMillis());
        context.setSign(headers.getFirst(Constants.SIGN_NAME));
        context.setAuthorization(headers.getFirst(authName));
        context.setRpcType(rpcType);
        context.setMethod(request.getMethodValue());
        context.setPath(request.getPath().value());
        exchange.getAttributes().put(Constants.FIBER_CONTEXT, context);
        log.info("context: {}", new Date());
        return Mono.just(this);
    }

    @Override
    public int index() {
        return FiberFilters.CONTEXT.getIndex();
    }

    @Override
    public String name() {
        return FiberFilters.CONTEXT.getName();
    }
}
