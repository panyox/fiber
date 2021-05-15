package com.fiber.demo.dubbo.service;

import com.fiber.demo.dubbo.model.User;
import com.fiber.demo.dubbo.model.UserPage;

import java.util.List;

/**
 * @author panyox
 */
public interface UserService {

    User getUserById(Integer id);

    User getUserByIdAndName(Integer id, String name);

    List<User> getUserList();

    UserPage getUserListPage();

    User addUser(User user);
}
