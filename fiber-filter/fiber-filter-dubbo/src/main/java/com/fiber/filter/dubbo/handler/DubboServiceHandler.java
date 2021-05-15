package com.fiber.filter.dubbo.handler;

import com.fiber.common.model.RouteData;
import com.fiber.filter.api.param.DubboParamBuilder;
import com.fiber.filter.dubbo.cache.ReferenceCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.web.server.ServerWebExchange;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @author panyox
 */
@Slf4j
public class DubboServiceHandler {

    private DubboParamBuilder paramBuilder;

    public DubboServiceHandler(DubboParamBuilder paramBuilder) {
        this.paramBuilder = paramBuilder;
    }

    public CompletableFuture<Object> invokeAsync(String parameters, RouteData routeData, ServerWebExchange exchange) {

        ReferenceConfig<GenericService> reference = ReferenceCache.getInstance().get(routeData.getId());
        if (Objects.isNull(reference) || StringUtils.isEmpty(reference.getInterface())) {
            reference = ReferenceCache.getInstance().init(routeData);
        }

        GenericService service = ReferenceConfigCache.getCache().get(reference);

        Pair<String[], Object[]> pair = paramBuilder.build(parameters, routeData.getParameters());
        return service.$invokeAsync(routeData.getMethodName(), pair.getLeft(), pair.getRight());
    }
}
