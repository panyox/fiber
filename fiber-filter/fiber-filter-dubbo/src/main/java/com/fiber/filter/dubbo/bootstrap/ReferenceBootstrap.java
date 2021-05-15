package com.fiber.filter.dubbo.bootstrap;

import com.fiber.common.config.RegistryCenterConfig;
import com.fiber.common.model.ServiceData;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author panyox
 */
@Slf4j
public class ReferenceBootstrap {

    //public static DubboBootstrap bootstrap = DubboBootstrap.getInstance();

    public static void init(RegistryCenterConfig registryConfig, List<ServiceData> serviceList) {
        log.info("init..");
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        List<ReferenceConfig> references = serviceList.stream().map(ReferenceBootstrap::initRefer).collect(Collectors.toList());
        bootstrap.application(new ApplicationConfig("fiber-consumer"))
                .registry(new RegistryConfig("zookeeper://" + registryConfig.getAddress()))
                .references(references)
                .start();
    }

    private static ReferenceConfig<GenericService> initRefer(ServiceData serviceData) {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface(serviceData.getName());
        reference.setGeneric("true");
        log.info("dubbo init service: {}", serviceData.getName());
        return reference;
    }

//    public static void destroy() {
//        bootstrap.destroy();
//    }

}
