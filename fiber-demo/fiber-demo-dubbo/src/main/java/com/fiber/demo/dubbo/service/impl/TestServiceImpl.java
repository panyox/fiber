package com.fiber.demo.dubbo.service.impl;

import com.fiber.demo.dubbo.service.TestService;
import com.fiber.rpc.dubbo.annotation.FiberService;
import com.fiber.rpc.dubbo.annotation.GetRoute;

/**
 * @author panyox
 */
@FiberService("test")
public class TestServiceImpl implements TestService {

    @Override
    @GetRoute("hello")
    public String test() {
        return "hello world";
    }
}
