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
    private String type;
    private String address;
    private Properties props = new Properties();
}
