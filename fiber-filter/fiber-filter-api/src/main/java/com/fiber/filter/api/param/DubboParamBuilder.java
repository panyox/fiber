package com.fiber.filter.api.param;

import com.fiber.common.model.RouteParam;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;


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
    Pair<String[], Object[]> build(String data, List<RouteParam> parameterTypes);
}
