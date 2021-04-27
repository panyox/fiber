package com.fiber.web.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author panyox
 */
public interface FiberHandler {

    Mono<ServerResponse> handle(ServerRequest request);

}
