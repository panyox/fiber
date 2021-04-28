package com.fiber.web.response;

import com.fiber.common.exception.FiberException;
import com.fiber.common.response.ResponseBuilder;

/**
 * @author panyox
 */
public class DefaultResponseBuilder implements ResponseBuilder<FiberResponse> {

    @Override
    public FiberResponse ok(Object data) {
        return FiberResponse.ok(data);
    }

    @Override
    public FiberResponse error(Throwable ex) {
        if (ex instanceof FiberException) {
            return FiberResponse.error(((FiberException) ex).getCode(), ex.getMessage());
        }
        return FiberResponse.error();
    }
}
