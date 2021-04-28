package com.fiber.filter.dubbo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author panyox
 */
@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private Integer age;
}
