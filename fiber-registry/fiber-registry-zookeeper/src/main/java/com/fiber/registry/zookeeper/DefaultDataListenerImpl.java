package com.fiber.registry.zookeeper;

/**
 * @author panyox
 */
public class DefaultDataListenerImpl implements DataListener {

    private EventDataHandler dataHandler;

    public DefaultDataListenerImpl(EventDataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    @Override
    public void dataChanged(String path, Object value, EventType eventType) {
        dataHandler.handle(path, value, eventType);
    }
}
