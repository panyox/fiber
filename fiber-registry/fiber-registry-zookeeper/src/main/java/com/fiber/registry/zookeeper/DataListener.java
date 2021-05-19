package com.fiber.registry.zookeeper;

/**
 * source code from https://github.com/apache/dubbo
 *
 * @author panyox
 */
public interface DataListener {
    void dataChanged(String path, Object value, EventType eventType);
}
