package com.fiber.filter.router.match;

import com.fiber.common.cache.RouteCache;
import com.fiber.common.constants.Constants;
import com.fiber.common.model.FiberContext;
import com.fiber.common.model.RouteData;
import com.fiber.common.model.ServiceData;
import com.fiber.common.utils.Md5Util;
import com.fiber.common.utils.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author panyox
 */
public class MatchRule {

    public static MatchRule INSTANCE = new MatchRule();

    public static MatchRule getInstance() {
        return INSTANCE;
    }

    public ServiceData matchService(FiberContext context) {
        String serviceId = buildServiceId(context);
        if (StringUtils.isEmpty(serviceId)) {
            return null;
        }
        return RouteCache.getInstance().getService(serviceId);
    }

    /**
     * eg: dubbo/v1/user
     *
     * @param context
     * @return
     */
    public String buildServiceId(FiberContext context) {
        String[] paths = context.getPath().split(Constants.SLASH);
        String id = "";
        if (paths.length > 1) {
            Pattern pattern = Pattern.compile(Constants.VERSION_REGX);
            Matcher matcher = pattern.matcher(paths[0]);
            String v = matcher.find() ? paths[0] : Constants.DEFAULT_VERSION;
            id = String.join(Constants.SLASH, context.getRpcType(), v, paths[1]);
        }
        return id;
    }

    public RouteData matchRoute(FiberContext context, List<RouteData> routes) {
        String routeId = buildRouteId(context);
        return routes.stream().filter(r -> r.getId().equals(routeId)).findFirst().orElse(null);
    }

    /**
     * id = dubbo/v1/user/balance#POST
     * md5(id)
     *
     * @param context
     * @return
     */
    public String buildRouteId(FiberContext context) {
        String path = String.join(Constants.SLASH, context.getRpcType(), context.getPath());
        String route = String.join(Constants.HASH_TAG, path, context.getMethod().toUpperCase());
        return Md5Util.md5DigestAsHex16(route);
    }
}
