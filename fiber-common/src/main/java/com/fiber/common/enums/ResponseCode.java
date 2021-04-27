package com.fiber.common.enums;

import lombok.Getter;

/**
 * @author panyox
 */
@Getter
public enum ResponseCode {

    SUCCESS(0, "success"),
    SERVICE_ENABLE(101, "Service is not available"),
    RULE_NOT_FOUND(102, "Path not found"),
    INNER_ERROR(500, "Internal error");

    private int code;
    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
