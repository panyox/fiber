package com.fiber.common.response;

/**
 * @author panyox
 */
public interface ResponseBuilder<T> {

    T ok(Object data);

    T error(Throwable error);
}
