package com.fiber.demo.bootstrap.result;

import com.fiber.common.response.ResponseBuilder;

/**
 * @author panyox
 */
public class CustomResultBuilder implements ResponseBuilder<Result> {

    @Override
    public Result ok(Object data) {
        return Result.ok(data);
    }

    @Override
    public Result error(Throwable err) {
        return Result.error(err.getMessage());
    }
}
