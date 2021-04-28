package com.fiber.common.enums;

import lombok.Getter;

/**
 * @author panyox
 */
@Getter
public enum RpcType {

    DUBBO("dubbo"),

    HTTP("http");

    private String name;

    RpcType(String name) {
        this.name = name;
    }
}
