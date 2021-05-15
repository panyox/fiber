package com.fiber.registry.zookeeper;


import com.fiber.common.model.MetadataInfo;

/**
 * @author panyox
 */
public class NodeBuilder {

    private static final String SLASH = "/";

    private static final String DOT = ".";

    private static final String ROOT = "/fiber";

    private static final String REGISTER_SERVICE_ROOT = ROOT + "/register/service";

    public static String buildMetaNode(MetadataInfo metadataInfo) {
        String nodeName = String.join(DOT, metadataInfo.getServiceName(), metadataInfo.getMethodName());
        return String.format("%s/%s", REGISTER_SERVICE_ROOT, nodeName);
    }
}
