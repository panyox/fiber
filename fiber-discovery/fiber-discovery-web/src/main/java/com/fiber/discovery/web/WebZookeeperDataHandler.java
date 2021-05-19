package com.fiber.discovery.web;

import com.alibaba.fastjson.JSON;
import com.fiber.common.model.MetadataInfo;
import com.fiber.registry.zookeeper.EventDataHandler;
import com.fiber.registry.zookeeper.EventType;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author panyox
 */
@Slf4j
public class WebZookeeperDataHandler implements EventDataHandler {

    private DubboReferenceBootstrap bootstrap;

    public WebZookeeperDataHandler(final DubboReferenceBootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public void handle(String path, String value, EventType eventType) {
        log.info("path: {} value: {}, type: {}", path, value, eventType);
        if (eventType.equals(EventType.NodeDeleted)) {
            //remove service
            return;
        }
        MetadataInfo metadata = JSON.parseObject(value, MetadataInfo.class);
        Objects.requireNonNull(metadata);
        bootstrap.start(metadata);
    }

    /**
     * set up a route
     *
     * @param metadataInfo
     */
    private void setupRoute(MetadataInfo metadataInfo) {

    }
}
