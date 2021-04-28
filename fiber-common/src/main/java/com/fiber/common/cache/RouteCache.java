package com.fiber.common.cache;

import com.fiber.common.model.RouteData;
import com.fiber.common.model.ServiceData;
import com.google.common.collect.Maps;

import java.util.concurrent.ConcurrentMap;

/**
 * @author panyox
 */
public class RouteCache {

    private static final RouteCache INSTANCE = new RouteCache();

    private static final ConcurrentMap<String, RouteData> ROUTE_MAP = Maps.newConcurrentMap();

    private static final ConcurrentMap<String, ServiceData> SERVICE_MAP = Maps.newConcurrentMap();

    public RouteCache() {

    }

    public static RouteCache getInstance() {
        return INSTANCE;
    }

    public void saveRoute(final RouteData data) {
        ROUTE_MAP.put(data.getId(), data);
    }

    public void removeRoute(final RouteData data) {
        ROUTE_MAP.remove(data.getId());
    }

    public RouteData getRoute(String id) {
        return ROUTE_MAP.get(id);
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
