package com.fiber.filter.dubbo.builder;

import com.fiber.common.model.RouteParam;
import com.fiber.common.utils.GsonUtil;
import com.fiber.common.utils.ListUtil;
import com.fiber.filter.api.param.DubboParamBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author panyox
 */
public class DefaultDubboParameterBuilder implements DubboParamBuilder {

    @Override
    public Pair<String[], Object[]> build(String data, List<RouteParam> parameterTypes) {
        if (StringUtils.isBlank(data) || parameterTypes == null || parameterTypes.size() == 0) {
            return new ImmutablePair<>(new String[]{}, new Object[]{});
        }
        Map<String, Object> dataMap = GsonUtil.getInstance().toObjectMap(data);
        String[] types = new String[parameterTypes.size()];
        Object[] objects = new Object[parameterTypes.size()];
        parameterTypes.forEach(ListUtil.listWithIndex((routeParam, index) -> {
            types[index] = routeParam.getType();
            Object obj = dataMap.get(routeParam.getName());
            if (Objects.isNull(obj)) {
                objects[index] = null;
            } else {
                if (obj instanceof JsonObject) {
                    objects[index] = GsonUtil.getInstance().convertToMap(obj.toString());
                } else if (obj instanceof JsonArray) {
                    objects[index] = GsonUtil.getInstance().fromList(obj.toString(), Object.class);
                } else {
                    objects[index] = obj;
                }
            }
        }));
        return new ImmutablePair<>(types, objects);
    }
}
