package com.fiber.discovery.web;

import com.fiber.common.model.MetadataInfo;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author panyox
 */
public class DubboReferenceBootstrap {

    private ApplicationConfig applicationConfig;

    private RegistryConfig registryConfig;

    public DubboReferenceBootstrap(ApplicationConfig applicationConfig, RegistryConfig registryConfig) {
        this.applicationConfig = applicationConfig;
        this.registryConfig = registryConfig;
    }

    public void start(MetadataInfo metadataInfo) {
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        ReferenceConfig<GenericService> reference = buildReference(metadataInfo);
        bootstrap.application(applicationConfig)
                .registry(registryConfig)
                .reference(reference)
                .start();
    }

    private ReferenceConfig<GenericService> buildReference(MetadataInfo metadataInfo) {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface(metadataInfo.getServiceName());
        reference.setGeneric("true");
        return reference;
    }
}
