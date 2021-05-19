package com.fiber.common.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author panyox
 */
@Data
@RequiredArgsConstructor
public class DubboRegistryConfig {
    private String protocol;
    private String host;
    private Integer port;

    public String getAddress() {
        return String.format("%s://%s:%s", protocol, host, port);
    }
}
