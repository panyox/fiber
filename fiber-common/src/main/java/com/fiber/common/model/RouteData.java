package com.fiber.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author panyox
 */
@Data
@NoArgsConstructor
public class RouteData implements Serializable {

    private String id;

    private String name;

    private String serviceId;

    private Boolean enable;

    private String serviceName;

    private String methodName;

    private String parameterTypes;

}
