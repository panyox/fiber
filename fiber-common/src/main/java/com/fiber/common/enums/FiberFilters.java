package com.fiber.common.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author panyox
 */
@Getter
@RequiredArgsConstructor
public enum FiberFilters {

    DUBBO(100, "dubbo");


    private int index;
    private String name;

    FiberFilters(int index, String name) {
        this.index = index;
        this.name = name;
    }
}
