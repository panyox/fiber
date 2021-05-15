package com.fiber.common.cache;

import com.fiber.common.model.RouteData;
import com.fiber.common.model.ServiceData;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @author panyox
 */
public class RouteCache {

    private static final RouteCache INSTANCE = new RouteCache();

    private static final ConcurrentMap<String, List<RouteData>> ROUTE_MAP = Maps.newConcurrentMap();

    private static final ConcurrentMap<String, ServiceData> SERVICE_MAP = Maps.newConcurrentMap();

    public RouteCache() {

    }

    public static RouteCache getInstance() {
        return INSTANCE;
    }

    public void saveRoute(final RouteData data) {
        String serviceId = data.getServiceId();
        if (ROUTE_MAP.containsKey(serviceId)) {
            List<RouteData> routes = ROUTE_MAP.get(serviceId);
            List<RouteData> newRoutes = routes.stream().filter(r -> !r.getId().equals(data.getId())).collect(Collectors.toList());
            newRoutes.add(data);
            ROUTE_MAP.put(serviceId, newRoutes);
        } else {
            ROUTE_MAP.put(serviceId, Lists.newArrayList(data));
        }
    }

    public void removeRoute(final String serviceId) {
        ROUTE_MAP.remove(serviceId);
    }

    public List<RouteData> getRoute(String serviceId) {
        return ROUTE_MAP.get(serviceId);
    }

    public void saveService(final ServiceData data) {
        SERVICE_MAP.put(data.getId(), data);
    }

    public void removeService(final ServiceData data) {
        SERVICE_MAP.remove(data.getId());
    }

    public ServiceData getService(String id) {
        return SERVICE_MAP.get(id);
    }
}
