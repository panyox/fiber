package com.fiber.filter.dubbo;

import com.fiber.common.constants.Constants;
import com.fiber.common.enums.FiberFilters;
import com.fiber.common.exception.FiberException;
import com.fiber.filter.api.FiberFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author panyox
 */
public class DubboFilter implements FiberFilter {

    @Override
    public Mono<FiberFilter> filter(ServerWebExchange exchange) {
        if (exchange.getRequest().getMethod().matches("GET")) {
            List<User> users = Arrays.asList(new User(1, "测试", 18), new User(2, "Markus", 32));
            exchange.getAttributes().put(Constants.FIBER_CONTENT, users);
        } else {
            throw new FiberException(101, "Not found");
        }
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
