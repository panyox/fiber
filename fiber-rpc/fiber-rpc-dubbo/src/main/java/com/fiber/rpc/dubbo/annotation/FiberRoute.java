package com.fiber.rpc.dubbo.annotation;

import com.fiber.common.enums.RouteMethod;

import java.lang.annotation.*;

/**
 * @author panyox
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface FiberRoute {

    String value() default "";

    String name() default "";

    RouteMethod method() default RouteMethod.GET;

    boolean enable() default true;

}
