package com.fiber.web.handler;

import com.fiber.common.constants.Constants;
import com.fiber.common.response.ResponseBuilder;
import com.fiber.filter.api.FiberFilter;
import com.fiber.filter.api.utils.SpringBeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

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
        ResponseBuilder resp = SpringBeanUtils.getInstance().getBean(ResponseBuilder.class);
        Object data = Optional.ofNullable(exchange.getAttributes().get(Constants.FIBER_ERROR))
                .map(e -> resp.error((Throwable) e))
                .orElseGet(() -> resp.ok(exchange.getAttributes().get(Constants.FIBER_CONTENT)));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(data));
    }

    private void handleError(Throwable ex, ServerWebExchange exchange) {
        ex.printStackTrace();
        exchange.getAttributes().put(Constants.FIBER_ERROR, ex);
    }

    private void handleDone(ServerWebExchange exchange) {
        //do something when all filter done
    }
}
