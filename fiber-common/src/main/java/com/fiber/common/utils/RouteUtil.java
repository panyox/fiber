package com.fiber.common.utils;

import com.fiber.common.model.FiberContext;

/**
 * @author panyox
 */
public class RouteUtil {

    public static String generateRouteId(FiberContext context) {
        String str = String.format("%s%s%s", context.getRpcType(), context.getMethod(), context.getPath());
        return Md5Util.md5DigestAsHex16(str);
    }

    public static String generateRouteId(String content) {
        return Md5Util.md5DigestAsHex16(content);
    }
}
