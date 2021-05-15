package com.fiber.demo.dubbo.service.impl;

import com.fiber.demo.dubbo.model.User;
import com.fiber.demo.dubbo.model.UserPage;
import com.fiber.demo.dubbo.service.UserService;
import com.fiber.rpc.dubbo.annotation.FiberService;
import com.fiber.rpc.dubbo.annotation.GetRoute;
import com.fiber.rpc.dubbo.annotation.PostRoute;

import java.util.Arrays;
import java.util.List;

/**
 * @author panyox
 */
@FiberService(value = "user", version = "v2")
public class UserServiceImpl implements UserService {

    @Override
    @GetRoute("getById")
    public User getUserById(Integer id) {
        return new User(id, "test");
    }

    @Override
    @GetRoute("getByIdAndName")
    public User getUserByIdAndName(Integer id, String name) {
        return new User(id, name);
    }

    @Override
    @GetRoute("list")
    public List<User> getUserList() {
        return Arrays.asList(new User(1, "Jack"), new User(2, "Rose"));
    }

    @Override
    @GetRoute("listPage")
    public UserPage getUserListPage() {
        UserPage up = new UserPage();
        up.setTotal(2);
        up.setUsers(Arrays.asList(new User(1, "Jack"), new User(2, "Rose")));
        return up;
    }

    @Override
    @PostRoute("add")
    public User addUser(User user) {
        return user;
    }
}
