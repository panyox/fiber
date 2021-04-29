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
@Route(method = RouteMethod.PUT)
public @interface PutRoute {

    String value() default "";

    String name() default "";
    
}
