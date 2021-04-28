package com.fiber.common.enums;

import lombok.Getter;

/**
 * @author panyox
 */
@Getter
public enum ResponseCode {

    SUCCESS(0, "success"),
    SERVICE_NOT_FOUND(100, "Service not found"),
    SERVICE_NOT_ABLE(101, "Service not able to use"),
    ROUTE_NOT_FOUND(102, "Route not found"),
    ROUTE_NOT_ABLE(103, "Route not able to use"),
    INNER_ERROR(500, "Internal error");

    private int code;
    private String message;

    ResponseCode() {

    }

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
