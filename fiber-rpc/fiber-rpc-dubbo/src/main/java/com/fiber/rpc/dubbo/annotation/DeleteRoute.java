package com.fiber.rpc.dubbo.annotation;

import com.fiber.common.enums.RouteMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author panyox
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@FiberRoute(method = RouteMethod.DELETE)
public @interface DeleteRoute {

    String value() default "";

    String name() default "";

}
