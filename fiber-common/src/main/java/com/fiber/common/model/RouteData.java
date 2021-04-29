package com.fiber.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author panyox
 */
@Data
@NoArgsConstructor
public class RouteData implements Serializable {

    private String id;

    private String name;

    private String path;

    private String serviceId;

    private String rpcType;

    private String httpMethod;

    private Boolean enable;

    private String serviceName;

    private String methodName;

    private List<RouteParam> parameters;

}
