package com.fiber.registry.zookeeper;

/**
 * @author panyox
 */
public interface EventDataHandler {

    void handle(String path, String value, EventType eventType);

}
