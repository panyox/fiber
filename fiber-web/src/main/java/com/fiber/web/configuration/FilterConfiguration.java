package com.fiber.web.configuration;

import com.fiber.filter.api.FiberFilter;
import com.fiber.filter.context.ContextFilter;
import com.fiber.filter.dubbo.DubboFilter;
import com.fiber.filter.router.RouteFilter;
import org.springframework.context.annotation.Bean;

/**
 * Fiber filter configuration
 *
 * @author panyox
 */
public class FilterConfiguration {

    /**
     * dubbo filter
     *
     * @return
     */
    @Bean
    public FiberFilter dubboFilter() {
        return new DubboFilter();
    }

    /**
     * context filter
     *
     * @return
     */
    @Bean
    public FiberFilter contextFilter() {
        return new ContextFilter();
    }

    /**
     * router filter
     *
     * @return
     */
    @Bean
    public FiberFilter routerFilter() {
        return new RouteFilter();
    }
}
