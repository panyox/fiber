package com.fiber.rpc.dubbo.register;

import com.fiber.common.model.MetadataInfo;
import com.fiber.common.utils.GsonUtil;
import com.fiber.registry.zookeeper.NodeBuilder;
import com.fiber.registry.zookeeper.ZkOperator;
import org.apache.curator.framework.CuratorFramework;

import java.nio.charset.StandardCharsets;

/**
 * @author panyox
 */
public class ZookeeperRegister {

    private CuratorFramework client;

    public ZookeeperRegister(CuratorFramework client) {
        this.client = client;
    }

    public void registerMetadata(MetadataInfo metadataInfo) {
        try {
            String path = NodeBuilder.buildMetaNode(metadataInfo);
            ZkOperator.createOrWrite(client, path, GsonUtil.getInstance().toJson(metadataInfo).getBytes(StandardCharsets.UTF_8));
            System.out.println("zookeeper service: " + metadataInfo.getServiceName() + " method: " + metadataInfo.getMethodName() + " register success!");
        } catch (Exception var1) {
            System.out.println("register metadata error: " + var1.getMessage() + " cause: " + var1.getCause() + " metadata: " + metadataInfo.toString());
        }
    }
}
