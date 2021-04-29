package com.fiber.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author panyox
 */
@Data
@NoArgsConstructor
public class RouteParam {
    /**
     * parameter name
     */
    private String name;

    /**
     * parameter type
     */
    private String type;

}
