package com.fiber.registry.zookeeper;


import com.fiber.common.constants.Constants;
import com.fiber.common.model.MetadataInfo;

/**
 * @author panyox
 */
public class NodeBuilder {

    /**
     * @param metadataInfo
     * @return
     */
    public static String buildMetaNode(MetadataInfo metadataInfo) {
        String nodeName = String.join(Constants.DOT, metadataInfo.getServiceName(), metadataInfo.getMethodName());
        return String.join(Constants.SLASH, Constants.ZK_DUBBO_SERVICE_ROOT, nodeName);
    }
}
