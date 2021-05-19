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
        service.setName("com.yox.service.DemoService");
        service.setConfig("");
        service.setEnable(true);

        RouteCache.getInstance().saveService(service);

        RouteData route = new RouteData();

        String rpcType = "dubbo";
        String method = "GET";
        String path = "/hello";
        String routeId = RouteUtil.generateRouteId(String.format("%s%s%s", rpcType, method, path));
        log.info("route id: {}", routeId);
        route.setId(routeId);
        route.setServiceId("123");
        route.setPath(path);
        route.setHttpMethod(method);
        route.setRpcType(rpcType);
        route.setMethodName("hello");
        route.setServiceName("com.yox.service.DemoService");
        route.setEnable(true);
        List<RouteParam> params = Arrays.asList(new RouteParam("name", "java.lang.String"));
        //route.setParameters(params);

        RouteCache.getInstance().saveRoute(route);

        RouteData route1 = new RouteData();

        String path1 = "/user/list";
        String routeId1 = RouteUtil.generateRouteId(String.format("%s%s%s", rpcType, method, path1));
        log.info("route id: {}", routeId1);
        route1.setId(routeId1);
        route1.setServiceId("123");
        route1.setPath(path1);
        route1.setHttpMethod(method);
        route1.setRpcType(rpcType);
        route1.setMethodName("userList");
        route1.setServiceName("com.yox.service.DemoService");
        route1.setEnable(true);

        RouteCache.getInstance().saveRoute(route1);

//        RegistryCenterConfig config = new RegistryCenterConfig();
//        config.setAddress("localhost:2181");
//        List<ServiceData> services = new ArrayList<>();
//        services.add(service);
//        ReferenceBootstrap.init(config, services);
    }
}
