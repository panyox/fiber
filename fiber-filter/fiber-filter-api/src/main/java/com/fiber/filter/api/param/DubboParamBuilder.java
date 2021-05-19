package com.fiber.filter.api.param;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;


/**
 * @author panyox
 */
public interface DubboParamBuilder {

    /**
     * dubbo proxy parameter builder
     *
     * @param data
     * @param parameterTypes
     * @return
     */
    Pair<String[], Object[]> build(String data, Map<String, String> parameters);
}
