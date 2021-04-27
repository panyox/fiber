package com.fiber.common.response;

/**
 * @author panyox
 */
public interface ResponseBuilder<T> {

    T ok(int code, String msg, Object data);

    T error(int code, String msg, Object data);
}
