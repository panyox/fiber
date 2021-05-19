package com.fiber.web.configuration;

import com.fiber.common.config.DubboRegistryConfig;
import com.fiber.common.config.RegistryCenterConfig;
import com.fiber.discovery.web.DubboReferenceBootstrap;
import com.fiber.discovery.web.WebZookeeperDataHandler;
import com.fiber.discovery.web.WebZookeeperDiscoveryClient;
import com.fiber.registry.zookeeper.EventDataHandler;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author panyox
 */
public class DiscoveryClientConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "fiber.router.register")
    public RegistryCenterConfig registryCenterConfig() {
        return new RegistryCenterConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "fiber.dubbo.register")
    public DubboRegistryConfig dubboRegistryConfig() {
        return new DubboRegistryConfig();
    }

    @Bean
    public DubboReferenceBootstrap dubboReferenceBootstrap(final DubboRegistryConfig config) {
        return new DubboReferenceBootstrap(new ApplicationConfig("fiber-router-consumer"), new RegistryConfig(config.getAddress()));
    }

    @Bean
    public EventDataHandler eventDataHandler(final DubboReferenceBootstrap bootstrap) {
        return new WebZookeeperDataHandler(bootstrap);
    }

    @Bean
    public WebZookeeperDiscoveryClient webZookeeperDiscoveryClient(final RegistryCenterConfig config, final EventDataHandler handler) {
        return new WebZookeeperDiscoveryClient(config, handler);
    }
}
