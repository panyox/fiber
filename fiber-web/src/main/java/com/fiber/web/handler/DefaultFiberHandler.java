package com.fiber.web.handler;

import com.fiber.common.constants.Constants;
import com.fiber.common.response.ResponseBuilder;
import com.fiber.common.response.ResponseCode;
import com.fiber.common.utils.JsonUtil;
import com.fiber.filter.api.AsyncFiberFilter;
import com.fiber.filter.api.FiberFilter;
import com.fiber.filter.api.utils.SpringBeanUtils;
import com.fiber.filter.dubbo.DubboFilter;
import com.fiber.web.response.FiberResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author panyox
 */
@Slf4j
public class DefaultFiberHandler implements FiberHandler {

    private List<FiberFilter> filters;

    public DefaultFiberHandler(List<FiberFilter> filters) {
        this.filters = filters;
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        return Mono.defer(() -> {
            ServerWebExchange exchange = request.exchange();
            Flux<FiberFilter> fls = Flux.fromStream(filters.stream());
            fls.subscribe(ft -> ft.filter(exchange),
                    err -> handleError(err, exchange),
                    () -> handleDone(exchange));
            try {
                ResponseBuilder resp = SpringBeanUtils.getInstance().getBean(ResponseBuilder.class);
                Object result;
                result = Optional.ofNullable(exchange.getAttributes().get(Constants.FIBER_ERROR))
                        .map(e -> resp.error((Throwable) e))
                        .orElse(null);
                if (Objects.isNull(result)) {
                    AsyncFiberFilter af = SpringBeanUtils.getInstance().getBean(DubboFilter.class);
                    result = Optional.ofNullable(af.result.get())
                            .map(r -> resp.ok(JsonUtil.removeClass(r)))
                            .orElse(FiberResponse.error(ResponseCode.RPC_NO_RESULT));
                }
                return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(result));
            } catch (Exception e) {
                log.error("resp error, cause: {}, msg: {}", e.getCause(), e.getMessage());
                return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(FiberResponse.error()));
            }
        });
    }

    private void handleError(Throwable ex, ServerWebExchange exchange) {
        ex.printStackTrace();
        exchange.getAttributes().put(Constants.FIBER_ERROR, ex);
    }

    private void handleDone(ServerWebExchange exchange) {
        //do something when all filter done
    }
}
