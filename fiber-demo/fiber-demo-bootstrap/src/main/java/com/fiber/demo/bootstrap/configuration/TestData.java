package com.fiber.demo.bootstrap.configuration;

import com.fiber.common.cache.RouteCache;
import com.fiber.common.model.RouteData;
import com.fiber.common.model.RouteParam;
import com.fiber.common.model.ServiceData;
import com.fiber.common.utils.RouteUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @author panyox
 */
@Slf4j
public class TestData {

    public void initRoute() {

        log.info("init test route");

        ServiceData service = new ServiceData();
        service.setId("123");
        service.setName("test");
        service.setConfig("");
        service.setEnable(true);

        RouteCache.getInstance().saveService(service);

        RouteData route = new RouteData();

        String rpcType = "dubbo";
        String method = "GET";
        String path = "/test";
        String routeId = RouteUtil.generateRouteId(String.format("%s%s%s", rpcType, method, path));
        log.info("route id: {}", routeId);
        route.setId(routeId);
        route.setServiceId("123");
        route.setPath(path);
        route.setHttpMethod(method);
        route.setRpcType(rpcType);
        route.setMethodName("test");
        route.setEnable(true);
        List<RouteParam> params = Arrays.asList(new RouteParam("id", "java.lang.Integer"), new RouteParam("name", "java.lang.String"));
        route.setParameters(params);
        RouteCache.getInstance().saveRoute(route);
    }
}
