package com.fiber.rpc.dubbo;

import com.fiber.common.config.RegistryCenterConfig;
import com.fiber.common.constants.Constants;
import com.fiber.common.enums.RpcType;
import com.fiber.common.exception.FiberException;
import com.fiber.common.model.MetadataInfo;
import com.fiber.common.thread.TaskExecutor;
import com.fiber.common.utils.StringUtils;
import com.fiber.registry.zookeeper.ZkClient;
import com.fiber.rpc.dubbo.annotation.FiberRoute;
import com.fiber.rpc.dubbo.annotation.FiberService;
import com.fiber.rpc.dubbo.register.ZookeeperRegister;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.dubbo.config.spring.ServiceBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author panyox
 */
@Slf4j
public class DubboServiceBeanListener implements ApplicationListener<ContextRefreshedEvent> {

    private ZookeeperRegister zookeeperRegister;

    private final AtomicBoolean registered = new AtomicBoolean(false);

    public DubboServiceBeanListener(RegistryCenterConfig registryCenterConfig) {
        CuratorFramework zkClient = ZkClient.createSimple(registryCenterConfig.getAddress());
        this.zookeeperRegister = new ZookeeperRegister(zkClient);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent context) {
        if (!registered.compareAndSet(false, true)) {
            return;
        }
        String port = Optional.ofNullable(context.getApplicationContext().getEnvironment().getProperty("server.port")).orElse("-1");
        String appName = Optional.of(context.getApplicationContext().getApplicationName()).orElse("appName");
        String host = StringUtils.getIP();
        MetadataInfo.AppInfo appInfo = MetadataInfo.AppInfo.builder()
                .host(host)
                .port(Integer.valueOf(port))
                .name(appName)
                .build();
        Map<String, ServiceBean> serviceBean = context.getApplicationContext().getBeansOfType(ServiceBean.class);
        for (Map.Entry<String, ServiceBean> entry : serviceBean.entrySet()) {
            TaskExecutor.me().execute(() -> processBean(entry.getValue(), appInfo));
        }
    }

    private void processBean(final ServiceBean serviceBean, final MetadataInfo.AppInfo appInfo) {
        Class<?> clazz = serviceBean.getRef().getClass();
        FiberService fs = clazz.getAnnotation(FiberService.class);
        if (Objects.nonNull(fs)) {
            String fsName = fs.value();
            if (StringUtils.isEmpty(fsName)) {
                throw new FiberException("FiberService value is null in " + serviceBean.getInterface());
            }
            String version = StringUtils.isEmpty(fs.version()) ? Constants.DEFAULT_VERSION : fs.version();
            MetadataInfo.ServiceInfo serviceInfo = MetadataInfo.ServiceInfo.builder()
                    .version(version)
                    .name(StringUtils.clearPath(fsName))
                    .desc(fs.desc())
                    .build();
            Method[] methods = ReflectionUtils.getUniqueDeclaredMethods(clazz);
            for (Method method : methods) {
                if (AnnotatedElementUtils.hasAnnotation(method, FiberRoute.class)) {
                    FiberRoute fiberRoute = AnnotatedElementUtils.getMergedAnnotation(method, FiberRoute.class);
                    if (Objects.nonNull(fiberRoute)) {
                        MetadataInfo metadataInfo = MetadataInfo.builder()
                                .rpcType(RpcType.DUBBO.getName())
                                .name(fiberRoute.name())
                                .path(StringUtils.clearPath(fiberRoute.value()))
                                .httpMethod(fiberRoute.method().name())
                                .serviceName(serviceBean.getInterface())
                                .methodName(method.getName())
                                .enable(fiberRoute.enable())
                                .serviceInfo(serviceInfo)
                                .appInfo(appInfo)
                                .build();
                        zookeeperRegister.registerMetadata(metadataInfo);
                    }
                }
            }
        }
    }
}
