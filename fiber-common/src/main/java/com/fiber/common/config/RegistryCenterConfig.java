package com.fiber.common.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

/**
 * @author panyox
 */
@Data
@RequiredArgsConstructor
public class RegistryCenterConfig {
    private String protocol;
    private String host;
    private Integer port;
    private Properties props = new Properties();
}
