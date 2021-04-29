package com.fiber.filter.data;

import com.fiber.common.constants.Constants;
import com.fiber.common.enums.FiberFilters;
import com.fiber.common.model.FiberContext;
import com.fiber.common.utils.GsonUtil;
import com.fiber.filter.api.FiberFilter;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author panyox
 */
public class DataFilter implements FiberFilter {

    private final List<HttpMessageReader<?>> messageReaders;

    public DataFilter() {
        this.messageReaders = HandlerStrategies.withDefaults().messageReaders();
    }

    @Override
    public Mono<FiberFilter> filter(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        FiberContext context = exchange.getAttribute(Constants.FIBER_CONTEXT);
        if (Objects.nonNull(context)) {
            MediaType mediaType = request.getHeaders().getContentType();
            ServerRequest serverRequest = ServerRequest.create(exchange, messageReaders);
            if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)) {
                return getBody(this, exchange, serverRequest);
            }
            if (MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(mediaType)) {
                return getForm(this, exchange, serverRequest);
            }
            return getQuery(this, exchange, serverRequest);
        }
        return Mono.just(this);
    }

    private Mono<FiberFilter> getBody(FiberFilter filter, ServerWebExchange exchange, ServerRequest request) {
        return request.bodyToMono(String.class)
                .switchIfEmpty(Mono.defer(() -> Mono.just("{}")))
                .flatMap(body -> {
                    exchange.getAttributes().put(Constants.BODY_DATA, body);
                    return Mono.just(filter);
                });
    }

    private Mono<FiberFilter> getForm(FiberFilter filter, ServerWebExchange exchange, ServerRequest request) {
        return request.formData()
                .switchIfEmpty(Mono.defer(() -> Mono.just(new LinkedMultiValueMap<>())))
                .flatMap(data -> {
                    exchange.getAttributes().put(Constants.FORM_DATA, toJsonMap(() -> data));
                    return Mono.just(filter);
                });
    }

    private Mono<FiberFilter> getQuery(FiberFilter filter, ServerWebExchange exchange, ServerRequest request) {
        exchange.getAttributes().put(Constants.QUERY_DATA, toJsonMap(request::queryParams));
        return Mono.just(filter);
    }

    private <K, V> String toJsonMap(final Supplier<MultiValueMap<K, V>> supplier) {
        return GsonUtil.getInstance().toJson(supplier.get().toSingleValueMap());
    }

    @Override
    public int index() {
        return FiberFilters.DATA.getIndex();
    }

    @Override
    public String name() {
        return FiberFilters.DATA.getName();
    }
}
