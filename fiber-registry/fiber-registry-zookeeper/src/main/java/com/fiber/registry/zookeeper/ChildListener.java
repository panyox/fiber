package com.fiber.registry.zookeeper;

import java.util.List;

/**
 * source code from https://github.com/apache/dubbo
 *
 * @author panyox
 */
public interface ChildListener {
    void childChanged(String path, List<String> children);
}
