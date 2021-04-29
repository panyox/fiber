package com.fiber.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author panyox
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Route(method = RouteMethod.POST)
public @interface PostRoute {

    String value() default "";

    String name() default "";

}
