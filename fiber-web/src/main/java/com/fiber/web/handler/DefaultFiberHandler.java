package com.fiber.web.handler;

import com.fiber.common.constants.Constants;
import com.fiber.common.response.ResponseBuilder;
import com.fiber.filter.api.FiberFilter;
import com.fiber.web.response.DefaultResponseBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author panyox
 */
public class DefaultFiberHandler implements FiberHandler {

    private List<FiberFilter> filters;

    public DefaultFiberHandler(List<FiberFilter> filters) {
        this.filters = filters;
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        ServerWebExchange exchange = request.exchange();
        Flux<FiberFilter> fls = Flux.fromStream(filters.stream());
        fls.subscribe(ft -> ft.filter(exchange),
                err -> handleError(err, exchange),
                () -> handleDone(exchange));
        ResponseBuilder responseBuilder = new DefaultResponseBuilder();
        //Object res = Objects.isNull(exchange.getAttributes().get(Constants.FiberError))?responseBuilder.ok();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue("test"));
    }

    private void handleError(Throwable ex, ServerWebExchange exchange) {
        exchange.getAttributes().put(Constants.FiberError, ex);
    }

    private void handleDone(ServerWebExchange exchange) {

    }
}
