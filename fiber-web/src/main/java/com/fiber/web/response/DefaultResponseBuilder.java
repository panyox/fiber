package com.fiber.web.response;

import com.fiber.common.response.ResponseBuilder;

/**
 * @author panyox
 */
public class DefaultResponseBuilder implements ResponseBuilder<FiberResponse> {

    @Override
    public FiberResponse ok(int code, String msg, Object data) {
        return null;
    }

    @Override
    public FiberResponse error(int code, String msg, Object data) {
        return null;
    }
}
