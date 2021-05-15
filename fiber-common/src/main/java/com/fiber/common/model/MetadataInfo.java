package com.fiber.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author panyox
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetadataInfo implements Serializable {

    private String name;
    private String rpcType;
    private String path;
    private String httpMethod;
    private String serviceName;
    private String methodName;
    private Map<String, String> params;
    private Boolean enable;
    private ServiceInfo serviceInfo;
    private AppInfo appInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ServiceInfo implements Serializable {
        private String version;
        private String name;
        private String desc;
        private Boolean enable;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AppInfo {
        private String name;
        private String host;
        private Integer port;
    }
}
