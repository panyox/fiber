package com.fiber.rpc.dubbo.register;

import com.fiber.common.model.MetadataInfo;
import com.fiber.common.utils.GsonUtil;
import com.fiber.registry.zookeeper.NodeBuilder;
import com.fiber.registry.zookeeper.curator.CuratorZookeeperClient;

/**
 * @author panyox
 */
public class ZookeeperRegister {

    private CuratorZookeeperClient client;

    public ZookeeperRegister(CuratorZookeeperClient client) {
        this.client = client;
    }

    public void registerMetadata(MetadataInfo metadataInfo) {
        try {
            String path = NodeBuilder.buildMetaNode(metadataInfo);
            client.createPersistent(path, GsonUtil.getInstance().toJson(metadataInfo));
            System.out.println("zookeeper service: " + metadataInfo.getServiceName() + " method: " + metadataInfo.getMethodName() + " register success!");
        } catch (Exception var1) {
            System.out.println("register metadata error: " + var1.getMessage() + " cause: " + var1.getCause() + " metadata: " + metadataInfo.toString());
        }
    }
}
