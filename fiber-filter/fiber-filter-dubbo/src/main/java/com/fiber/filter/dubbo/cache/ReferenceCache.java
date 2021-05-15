package com.fiber.filter.dubbo.cache;

import com.fiber.common.exception.FiberException;
import com.fiber.common.model.RouteData;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.concurrent.ExecutionException;

/**
 * @author panyox
 */
public class ReferenceCache {

    private final int maxCount = 10000;

    private final LoadingCache<String, ReferenceConfig<GenericService>> cache = CacheBuilder.newBuilder()
            .maximumWeight(maxCount)
            .weigher((Weigher<String, ReferenceConfig<GenericService>>) (string, referenceConfig) -> getSize())
            .build((new CacheLoader<String, ReferenceConfig<GenericService>>() {
                @Override
                public ReferenceConfig<GenericService> load(final String key) {
                    return new ReferenceConfig<>();
                }
            }));

    private int getSize() {
        return (int) cache.size();
    }

    public static ReferenceCache getInstance() {
        return ReferenceCacheInstance.INSTANCE;
    }

    public <T> ReferenceConfig<T> get(final String id) {
        try {
            return (ReferenceConfig<T>) cache.get(id);
        } catch (ExecutionException e) {
            throw new FiberException(e.getCause());
        }
    }

    public ReferenceConfig<GenericService> init(final RouteData routeData) {
        try {
            ReferenceConfig<GenericService> reference = cache.get(routeData.getId());
            if (StringUtils.isNoneBlank(reference.getInterface())) {
                return reference;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return initReference(routeData);
    }

    private ReferenceConfig<GenericService> initReference(final RouteData routeData) {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface(routeData.getServiceName());
        reference.setGeneric("true");
        cache.put(routeData.getId(), reference);
        return reference;
    }

    static class ReferenceCacheInstance {

        static final ReferenceCache INSTANCE = new ReferenceCache();

    }
}
