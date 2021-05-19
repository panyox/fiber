package com.fiber.discovery.web;

import com.fiber.common.config.RegistryCenterConfig;
import com.fiber.common.constants.Constants;
import com.fiber.common.model.URL;
import com.fiber.registry.zookeeper.DefaultDataListenerImpl;
import com.fiber.registry.zookeeper.EventDataHandler;
import com.fiber.registry.zookeeper.curator.CuratorZookeeperClient;

/**
 * @author panyox
 */
public class WebZookeeperDiscoveryClient {

    public WebZookeeperDiscoveryClient(final RegistryCenterConfig config, final EventDataHandler dataHandler) {
        CuratorZookeeperClient client = new CuratorZookeeperClient(new URL(config.getHost(), config.getPort()));
        client.addTargetDataListener(Constants.ZK_DUBBO_SERVICE_ROOT, new DefaultDataListenerImpl(dataHandler));
    }
}
