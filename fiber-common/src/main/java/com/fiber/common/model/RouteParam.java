package com.fiber.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author panyox
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
