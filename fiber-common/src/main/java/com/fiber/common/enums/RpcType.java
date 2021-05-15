package com.fiber.common.enums;

import lombok.Getter;

/**
 * @author panyox
 */
@Getter
public enum RpcType {

    DUBBO("dubbo"),
    SPRING_CLOUD("spring_cloud"),
    SOFA("sofa"),
    HTTP("http");

    private String name;

    RpcType(String name) {
        this.name = name;
    }
}
