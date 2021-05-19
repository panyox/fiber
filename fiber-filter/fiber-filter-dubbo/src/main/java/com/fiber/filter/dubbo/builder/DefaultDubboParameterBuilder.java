package com.fiber.filter.dubbo.builder;

import com.fiber.common.utils.GsonUtil;
import com.fiber.filter.api.param.DubboParamBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.Objects;

/**
 * @author panyox
 */
public class DefaultDubboParameterBuilder implements DubboParamBuilder {

    @Override
    public Pair<String[], Object[]> build(String data, Map<String, String> parameters) {
        if (StringUtils.isBlank(data) || parameters == null) {
            return new ImmutablePair<>(new String[]{}, new Object[]{});
        }
        Map<String, Object> dataMap = GsonUtil.getInstance().toObjectMap(data);
        String[] types = new String[parameters.size()];
        Object[] objects = new Object[parameters.size()];

        int i = 0;
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            types[i] = entry.getValue();
            Object obj = dataMap.get(entry.getKey());
            if (Objects.isNull(obj)) {
                objects[i] = null;
            } else {
                if (obj instanceof JsonObject) {
                    objects[i] = GsonUtil.getInstance().convertToMap(obj.toString());
                } else if (obj instanceof JsonArray) {
                    objects[i] = GsonUtil.getInstance().fromList(obj.toString(), Object.class);
                } else {
                    objects[i] = obj;
                }
            }
            i++;
        }
        return new ImmutablePair<>(types, objects);
    }
}
