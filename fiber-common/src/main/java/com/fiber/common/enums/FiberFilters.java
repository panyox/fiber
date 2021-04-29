package com.fiber.common.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author panyox
 */
@Getter
@RequiredArgsConstructor
public enum FiberFilters {

    CONTEXT(0, "context"),
    ROUTER(1, "router"),
    DATA(2, "data"),
    DUBBO(50, "dubbo");
    
    private int index;
    private String name;

    FiberFilters(int index, String name) {
        this.index = index;
        this.name = name;
    }
}
