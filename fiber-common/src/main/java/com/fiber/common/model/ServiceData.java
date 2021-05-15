package com.fiber.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author panyox
 */
@Data
@NoArgsConstructor
public class ServiceData implements Serializable {

    private String id;

    private String name;

    private String version;

    private String interfaceName;

    private Boolean enable;

    private String config;

    private String desc;

}
