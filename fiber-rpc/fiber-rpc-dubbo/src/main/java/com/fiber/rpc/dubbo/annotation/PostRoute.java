package com.fiber.rpc.dubbo.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author panyox
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@FiberRoute(method = RouteMethod.POST)
public @interface PostRoute {

    @AliasFor(annotation = FiberRoute.class)
    String value() default "";

    String name() default "";

}
