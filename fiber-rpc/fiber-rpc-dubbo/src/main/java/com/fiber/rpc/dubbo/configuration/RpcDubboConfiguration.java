package com.fiber.rpc.dubbo.configuration;

import com.fiber.common.config.RegistryCenterConfig;
import com.fiber.rpc.dubbo.DubboServiceBeanListener;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author panyox
 */
@Configuration
public class RpcDubboConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "fiber.registry")
    public RegistryCenterConfig registryCenterConfig() {
        return new RegistryCenterConfig();
    }

    @Bean
    public DubboServiceBeanListener dubboServiceBeanListener(RegistryCenterConfig registryCenterConfig) {
        return new DubboServiceBeanListener(registryCenterConfig);
    }
}
