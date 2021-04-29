package com.fiber.web.configuration;

import com.fiber.filter.api.FiberFilter;
import com.fiber.filter.api.param.DubboParamBuilder;
import com.fiber.filter.context.ContextFilter;
import com.fiber.filter.data.DataFilter;
import com.fiber.filter.dubbo.DubboFilter;
import com.fiber.filter.dubbo.builder.DefaultDubboParameterBuilder;
import com.fiber.filter.dubbo.handler.DubboServiceHandler;
import com.fiber.filter.router.RouteFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.context.annotation.Bean;

/**
 * Fiber filter configuration
 *
 * @author panyox
 */
public class FilterConfiguration {

    @Bean
    @ConditionalOnMissingBean(value = DubboParamBuilder.class, search = SearchStrategy.ALL)
    public DubboParamBuilder dubboParamBuilder() {
        return new DefaultDubboParameterBuilder();
    }

    @Bean
    public DubboServiceHandler dubboServiceHandler(final DubboParamBuilder paramBuilder) {
        return new DubboServiceHandler(paramBuilder);
    }

    /**
     * dubbo filter
     *
     * @return
     */
    @Bean
    public FiberFilter dubboFilter(final DubboServiceHandler serviceHandler) {
        return new DubboFilter(serviceHandler);
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

    /**
     * data transform filter
     *
     * @return
     */
    @Bean
    public FiberFilter dataFilter() {
        return new DataFilter();
    }
}
