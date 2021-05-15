package com.fiber.filter.api;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * @author panyox
 */
public abstract class AsyncFiberFilter implements FiberFilter {

    public CompletableFuture<Object> result;

    public abstract Mono<FiberFilter> async(CompletableFuture<Object> future, ServerWebExchange exchange);

    @Override
    public Mono<FiberFilter> filter(ServerWebExchange exchange) {
        result = new CompletableFuture<>();
        return async(result, exchange);
    }

}
