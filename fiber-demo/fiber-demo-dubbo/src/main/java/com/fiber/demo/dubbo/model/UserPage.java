package com.fiber.demo.dubbo.model;

import lombok.Data;

import java.util.List;

/**
 * @author panyox
 */
@Data
public class UserPage {
    private Integer total;
    private List<User> users;
}
